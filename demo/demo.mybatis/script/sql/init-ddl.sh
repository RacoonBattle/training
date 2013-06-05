#!/bin/bash


if [ $# -lt 2 ]; then
    echo "usage: ./init-ddl.sh [local|dev|beta] <schmea file>"
    exit 1
fi

case $1 in
	local)
	source ../etc/local.conf
	;;

    dev)
	source ../etc/dev.conf
    ;;

    beta)
    source ../etc/beta.conf
    ;;

    *)
    echo "usage: ./init-ddl.sh [local|dev|beta] <schmea file>"
    exit 1
    ;;
esac

schema=$(<$2)

echo $DEST_CMD

echo $schema|$DEST_CMD
