create table tracking_TrackerEntry (
	trackerEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	eventDate DATE null,
	eventType VARCHAR(75) null,
	ipAddress VARCHAR(75) null
);