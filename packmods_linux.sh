#!/bin/bash
# This script will pack all mods and write them to an output directory.
# On first execution, the script will ask for the Starbound install location and optionally store it for future use.

function help {
        echo "help            - Display this message and exit."
        echo "nosavedlocation - Prompt for a location instead of using the saved location."
        exit
}

function get_asset_packer_location {
        # If a location is stored and the user didn't specify not to use it, use it.
        if [ -f .starboundlocation.txt ] && [ "${1,,}" != "nosavedlocation" ]; then
                sb_loc=$(cat .starboundlocation.txt)
        else
                # Otherwise prompt for a new location and store it.
                read -p "Please enter the path to your Starbound installation: " sb_loc
                echo ${sb_loc} > .starboundlocation.txt
        fi
}

function pack_mods {
        # Remove the output directory if it exists then create it.
        if [ -d "output" ]; then
                rm -r output
        fi
        mkdir output

        for dir in ./mods/*; do
                # Ensure we're only iterating over directories.
                [ -d "${dir}" ] || continue
                dirname=$(basename "${dir}")
                "${sb_loc}/linux/asset_packer" "./${dir}" "./output/${dirname}.pak"
        done
}

if [ "${1,,}" == "help" ]; then
        help
fi

get_asset_packer_location $1
pack_mods

