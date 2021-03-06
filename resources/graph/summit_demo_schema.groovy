/system.graph('summitDemo').option("graph.replication_config").set("{'class' : 'NetworkTopologyStrategy', 'Analytics + Graph' : 3, 'Cassandra + Graph' : 3 ,'Search + Graph' : 3}").ifNotExists().create()
//system.graph('summitDemo').ifNotExists().create()

schema.config().option('graph.schema_mode').set('Development')


// schema details can be run via DSE Studio
// Before populating graph, the schema must be propagated to all nodes
// wait 2-3 minutes before running loader code

schema.propertyKey("dateJoined").Timestamp().single().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("company").Text().single().create()
schema.propertyKey("location").Text().single().create()
schema.propertyKey("account").Text().single().create()
schema.edgeLabel("develops_in").multiple().create()
schema.edgeLabel("contributor").multiple().create()
schema.edgeLabel("following").multiple().create()
schema.edgeLabel("attending").multiple().create()
schema.edgeLabel("follows").multiple().create()
schema.edgeLabel("commiter").multiple().create()
schema.vertexLabel("github_user").properties("company", "location", "account", "dateJoined").create()
schema.vertexLabel("github_user").index("by_github_user_acct").materialized().by("account").add()
schema.vertexLabel("coding_language").properties("name").create()
schema.vertexLabel("coding_language").index("by_name").materialized().by("name").add()
schema.vertexLabel("project").create()
schema.vertexLabel("cassandra_summit").properties("name").create()
schema.vertexLabel("cassandra_summit").index("by_name").materialized().by("name").add()
schema.edgeLabel("develops_in").connection("github_user", "coding_language").add()
schema.edgeLabel("contributor").connection("github_user", "project").add()
schema.edgeLabel("following").connection("github_user", "github_user").add()
schema.edgeLabel("attending").connection("github_user", "cassandra_summit").add()
schema.edgeLabel("follows").connection("github_user", "github_user").add()
schema.edgeLabel("commiter").connection("github_user", "project").add()

schema.propertyKey("vehicle_id").Text().single().create()
schema.propertyKey("time_period").Timestamp().single().create()
schema.propertyKey("collect_time").Timestamp().single().create()
schema.propertyKey("event_name").Text().single().create()
schema.propertyKey("event_value").Text().single().create()
schema.propertyKey("elapsed_time").Int().single().create()


//Define our powertrain_events Vertex
schema.vertexLabel("powertrain_events").properties("vehicle_id", "time_period", "collect_time", "event_name", "event_value", "elapsed_time").create();

//Define our Edge
schema.edgeLabel("has_events").connection("github_user","powertrain_events").create()


//Define relevant indicies
schema.vertexLabel('powertrain_events').index('byvehicle_id').materialized().by('vehicle_id').add()
schema.vertexLabel('powertrain_events').index('byevent_name').secondary().by('event_name').add()
