IP=172.31.12.239
echo "current location"
dsetool -h $IP unload_core vehicle_tracking_app.current_location
dsetool -h $IP create_core vehicle_tracking_app.current_location reindex=true schema=resources/solr/geo.xml solrconfig=resources/solr/solrconfig.xml
echo "stats"
dsetool -h $IP unload_core vehicle_tracking_app.vehicle_stats
dsetool -h $IP create_core vehicle_tracking_app.vehicle_stats reindex=true schema=resources/solr/geo_vehicle.xml solrconfig=resources/solr/solrconfig.xml
echo "events"
dsetool -h $IP unload_core vehicle_tracking_app.vehicle_events
dsetool -h $IP create_core vehicle_tracking_app.vehicle_events reindex=true schema=resources/solr/events.xml solrconfig=resources/solr/solrconfig.xml
