#!/usr/bin/env bash

basedir=`dirname $0`
basedir=`cd $basedir;pwd`
template="$1"

#what doese this function do?
#删除转移符么
function remove_quotes()
{
    ret="$1"
    ret=${ret#\"}
    ret=${ret%\"}
    echo $ret
}

#give default value to ICCSTOOL, try to get value from ENV first
[ -n "$ICCSTOOL" ] || ICCSTOOL="$basedir/iccstool"

TMPFILE=$template.tmp.iccs$$
cp $template $TMPFILEl

#uniq cannot be used alone, must be used with sort
#while read p means alias matching word as p
#grep regex is different from defaul regrex, \+ sould be + in default regex
grep -o -e "%{iccs:[^|}]\+|[^|}]\+}%" $template | sort | uniq | while read p; do
    echo "matching word is $p"
    KEY="${p#"%{iccs:"}"
    KEY="${KEY%"}%"}"
    ICCSKEY="${KEY%%|*}"
    DEFAULTVALUE="${KEY#*|}"

## how to return value from iccstool?
    ICCSVALUE="$($ICCSTOOL "$ICCSKEY")"

    if [ $? != 0 ] || [ -z "$ICCSVALUE" ]; then
        echo "Unable to fetch $ICCSKEY, using default value $DEFAULTVALUE" >&2
        ICCSVALUE="$DEFAULTVALUE"
    fi

    echo "Replacing $p with $ICCSVALUE" >&2
    ICCSVALUE="$(remove_quotes "$ICCSVALUE")"

    ESCAPED_ICCSVALUE="$(echo "$ICCSVALUE" | sed "s/\//\\\\\//g")"
    ESCAPED_P="$(echo "$p" | sed "s/\//\\\\\//g")"

    sed "s/$ESCAPED_P/$ESCAPED_ICCSVALUE/g" $TMPFILE > $TMPFILE.1 || { echo "Failed" >&2; exit 1; }

    mv $TMPFILE.1 $TMPFILE
done

cat $TMPFILE || { echo "Failed" >&2; exit 1; }
unlink $TMPFILE
exit 0