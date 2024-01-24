#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

lscpu_out=`lscpu`
df_out=`df -BM /`
hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)  
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3}' | xargs | sed 's/K//')
total_mem=$(echo "$vmstat_mb" | tail -1 | awk '{print $4}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

export PGPASSWORD=$psql_password
host_id=$(psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -t -c "SELECT id FROM host_info WHERE hostname='$hostname'")

insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, \"timestamp\", total_mem) VALUES ('$hostname','$cpu_number','$cpu_architecture','$cpu_model','$cpu_mhz','$l2_cache','$timestamp','$total_mem');"


psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
