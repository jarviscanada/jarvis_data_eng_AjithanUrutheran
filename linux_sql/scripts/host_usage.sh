#!/bin/bash

#CLI Arguement
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Validate Arguement
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

#Retrieve Resource Usage
lscpu_out=`lscpu`
df_out=`df -BM /`

hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)

memory_free=$(echo "$vmstat_mb" | tail -1 | awk -v col="4" '{print $col}')
cpu_idle=$(echo "$vmstat_mb"  | tail -1 | awk -v col="15" '{print $col}')
cpu_kernel=$(echo "$vmstat_mb"  | tail -1 | awk -v col="14" '{print $col}')
disk_io=$(echo "$vmstat_mb" -d | tail -1 | awk -v col="10" '{print $col}')
disk_available=$(echo "$df_out" | tail -1 | awk '{print $4}' | xargs | sed 's/M//')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

#Execute statements
export PGPASSWORD=$psql_password
host_id=$(psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -t -c "SELECT id FROM host_info WHERE hostname='$hostname'")

insert_stmt="INSERT INTO host_usage(\"timestamp\",host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES ('$timestamp','$host_id','$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available');"


psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?
