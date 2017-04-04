CREATE KEYSPACE IF NOT EXISTS strategist WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;
CREATE TABLE IF NOT EXISTS strategist.studentTimeline (
    id timeuuid,
	studentId text,
	title text,
    description text,
	badgeString text,
	eventCreatedBy text,
	eventCreatorProfileUrl text,
	eventCreatorImagePath text,
	eventType text,
	eventCreationDateTime timestamp,
    PRIMARY KEY (studentId, id, eventCreationDateTime)
) WITH CLUSTERING ORDER BY (id DESC, eventCreationDateTime DESC);