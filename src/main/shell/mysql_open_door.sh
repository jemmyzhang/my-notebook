#!/bin/bash
#
#**********************************************************
# Author        : Jemmy Zhang
# Email         : jemmyzhangcn@foxmail.com
# Last modified : 2018-08-18 23:20
# Filename      : mysql_open_door
# Description   : designed for ubuntu 12.04 and mysql 5.5
#**********************************************************

ROOT_USER='root'
PASSWD='root'
LOGIN='_horizon'
OPEN='open'
CLOSE='close'
TYPE=$OPEN
MYSQL_CONF=/etc/mysql/my.cnf

show_help(){
echo "
Mysql Open Door Script.

The script can open mysql bind-adress to any remote network and add a user with all privileges to access mysql database.

Usage: $0 [options]

Options:
-h    show help message.
-c    change mysql bind-adress to localhost and delete the specified user( through -l option).
-l    specify the username you want to login the mysql. The username will start with a \'_\' in case of conflict. The defualt username is _horizon.
-o    change mysql bind-adress to 0.0.0.0 and add a full privileged user. The defualt user to add is $LOGIN.
-p    you need provide a root user password to run the script. The default root user password is root.
-u    you need provide a root user to run the script. The default root user is root.
"
exit 0
}

fail_show(){
result_code=$1
fail_message=$2
if [ $result_code -ne 0 ]
then
  echo $fail_message
  exit 1
fi
}

normalize_login_account(){
raw_input=$@
head=${raw_input:0:1}
if [ $head == '_' ]
then
  LOGIN=$raw_input
else
  LOGIN='_'$raw_input
fi
}

mysql_open_door(){
mysql -u$ROOT_USER -p$PASSWD << EOF
delimiter //
use mysql //
drop procedure if exists mysql_open_door //
create procedure mysql_open_door()
begin
delete from mysql.user where User='';
grant all privileges on *.* to '$LOGIN'@'%' identified by '$LOGIN';
flush privileges;
end;
//
call mysql_open_door() //
EOF
fail_show $? "Fail to add the mysql user $LOGIN."
}

mysql_close_door(){
mysql -u$ROOT_USER -p$PASSWD << EOF
delimiter //
use mysql //
drop procedure if exists mysql_close_door //
create procedure mysql_close_door()
begin
if exists (select 1 from mysql.user where User='$LOGIN') then
drop user $LOGIN;
flush privileges;
end if;
end;
//
call mysql_close_door() //
EOF
fail_show $? "Fail to delete the mysql user $LOGIN."
}

mysql_open_bind(){
/bin/grep "^\s*bind-address\s*=.*" $MYSQL_CONF >/dev/null 2>&1 && sed -i 's/^\s*\(bind-address\)\s*=.*/\1=0.0.0.0/g' $MYSQL_CONF || echo "bind-address=0.0.0.0" >>$MYSQL_CONF
/sbin/initctl stop mysql >/dev/null 2>&1
/sbin/initctl start mysql >/dev/null 2>&1
fail_show $? "Fail to open mysql bind-address."
}

mysql_close_bind(){
/bin/grep "^\s*bind-address\s*=.*" $MYSQL_CONF >/dev/null 2>&1 && sed -i 's/^\s*\(bind-address\)\s*=.*/\1=127.0.0.1/g' $MYSQL_CONF
/sbin/initctl stop mysql >/dev/null 2>&1
/sbin/initctl start mysql >/dev/null 2>&1
fail_show $? "Fail to close mysql bind-address"
}

check_service(){
/sbin/initctl list | grep mysql >/dev/null 2>&1
fail_show $? "Mysql Service not found. Please check mysql installed and service registed."
ls $MYSQL_CONF >/dev/null 2>&1
fail_show $? "Mysql configuration does not found. Please check MSQL_CONF path."
}

main(){
check_service
if [ $TYPE = $OPEN ]
then
  mysql_open_door
  mysql_open_bind
  echo "Successfully add a mysql user. The username is [ $LOGIN ] and the password is [ $LOGIN ]. Mysql is now open a remote access. Please enjoy."
elif [ $TYPE = $CLOSE ]
then
  mysql_close_door
  mysql_close_bind
  echo "Successfully delete user [ $LOGIN ] and mysql is now close the remote access."
else
  echo "[type] argument not illegal, [type] must be open or close."
  exit 1
fi

}

while getopts 'u:p:ol:t:hc' OPT;do
  case $OPT in
    u) ROOT_USER=$OPTARG;;
    p) PASSWD=$OPTARG;;
    o) TYPE=$OPEN;;
    l) normalize_login_account $OPTARG;;
    c) TYPE=$CLOSE;; 
    h) show_help;;
    ?) echo "error args";show_help;
  esac
done

main
