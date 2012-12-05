#!/bin/bash
# This bash script downloads and configures the TSPLIB problem instances for use by
# the TSPLIB4J library.  Users should familiarize themselves with the TSPLIB
# license agreement prior to running this script.

TYPES=("tsp" "hcp" "atsp" "sop" "vrp")
URLS=("http://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/tsp/ALL_tsp.tar.gz"
      "http://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/hcp/ALL_hcp.tar"
      "http://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/atsp/ALL_atsp.tar"
      "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/sop/ALL_sop.tar"
      "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/vrp/ALL_vrp.tar")

echo "This script will download all TSPLIB instances from"
echo "<http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/>."
echo "Download only if you agree to the license terms for TSPLIB."
echo ""

# prompt user to continue downloading
read -n 1 -p "Continue downloading TSPLIB problem instances (y/n)? " ANSWER
echo ""
case $ANSWER in
	[Yy]* ) echo "";;
	[Nn]* ) echo "Exiting."; exit;;
	* ) echo "Unknown option ${ANSWER}, exiting."; exit;;
esac

# download each of the TSPLIB problem types
for INDEX in "${!TYPES[@]}"; do
	TYPE=${TYPES[$INDEX]}
	URL=${URLS[$INDEX]}
	FILENAME=${URL##*/}

	# create the directory
	mkdir -p "data/${TYPE}"

	# download the problem instance TAR archive
	echo -n "Downloading ${TYPE} instances..."
	wget -q -O "data/${TYPE}/${FILENAME}" "${URL}"
	echo "done."

	echo -n "Extracting ${TYPE} instances..."

	# if the TAR archive is gzipped, first uncompress
	if [[ "data/${TYPE}/${FILENAME}" == *.gz ]]; then
		gunzip -f "data/${TYPE}/${FILENAME}"
	fi

	# then extract the TAR archive
	tar -xf "data/${TYPE}/ALL_${TYPE}.tar" -C "data/${TYPE}"
	rm "data/${TYPE}/ALL_${TYPE}.tar"

	# uncompress each instance contained in the TAR archive
	for FILE in data/${TYPE}/*.gz; do
		gunzip -f "${FILE}"
	
	done

	echo "done."
	echo ""
done


