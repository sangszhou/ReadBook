#!/usr/bin/env bash

str=abc.out


echo ${str%.*}

#不包含 . 的所有字符
echo $str | grep -o '^[^\.]*'

#echo $str | sed -E 's/(.*?)\..*/\1/'

#分隔符是 . 的所有字符集
echo $str | awk -F. '{print $1}'

#cut 命令闻所未闻
echo $str | cut -d. -f1

