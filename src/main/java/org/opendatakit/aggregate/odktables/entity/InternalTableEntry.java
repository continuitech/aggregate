package org.opendatakit.aggregate.odktables.entity;

import org.opendatakit.aggregate.odktables.relation.TableEntries;
import org.opendatakit.common.ermodel.simple.Entity;
import org.opendatakit.common.ermodel.simple.typedentity.TypedEntity;
import org.opendatakit.common.persistence.exception.ODKDatastoreException;
import org.opendatakit.common.web.CallingContext;

/**
 * <p>
 * An InternalTableEntry is a (aggregateTableIdentifier, ownerAggregate
 * Identifier, tableName, modificationNumber, isSynchronized, properties) tuple,
 * where
 * <ul>
 * <li>aggregateTableIdentifier: the globally unique identifer of a table</li>
 * <li>ownerAggregate Identifier: the globally unique identifier of the user who
 * owns the table</li>
 * <li>tableName: the human readable name of the table</li>
 * <li>modificationNumber: the current modification number. This should be
 * incremented every time the table is edited.</li>
 * <li>isSynchronized: true if the table is a synchronized table</li>
 * <li>properties: arbitrary metadata the client wants to store on the table</li>
 * </ul>
 * </p>
 * 
 * @author the.dylan.price@gmail.com
 * 
 */
public class InternalTableEntry extends TypedEntity {

    public InternalTableEntry(String aggregateOwnerIdentifier,
	    String tableName, boolean isSynchronized, String properties,
	    CallingContext cc) throws ODKDatastoreException {
	super(TableEntries.getInstance(cc).newEntity());
	setAggregateOwnerIdentifier(aggregateOwnerIdentifier);
	setName(tableName);
	setModificationNumber(0);
	setSynchronized(isSynchronized);
	setProperties(properties);
    }

    public InternalTableEntry(Entity entity) throws ODKDatastoreException {
	super(entity);
    }

    public String getAggregateOwnerIdentifier() {
	return entity.getString(TableEntries.AGGREGATE_OWNER_IDENTIFIER);
    }

    public void setAggregateOwnerIdentifier(String value) {
	entity.set(TableEntries.AGGREGATE_OWNER_IDENTIFIER, value);
    }

    public String getName() {
	return entity.getString(TableEntries.TABLE_NAME);
    }

    public void setName(String value) {
	entity.set(TableEntries.TABLE_NAME, value);
    }

    public int getModificationNumber() {
	return entity.getInteger(TableEntries.MODIFICATION_NUMBER);
    }

    public void setModificationNumber(int value) {
	entity.set(TableEntries.MODIFICATION_NUMBER, value);
    }

    public boolean isSynchronized() {
	return entity.getBoolean(TableEntries.IS_SYNCHRONIZED);
    }

    public void setSynchronized(boolean value) {
	entity.set(TableEntries.IS_SYNCHRONIZED, value);
    }

    public String getProperties() {
	return entity.getString(TableEntries.PROPERTIES);
    }

    public void setProperties(String value) {
	entity.set(TableEntries.PROPERTIES, value);
    }

    @Override
    public String toString() {
	return String
		.format("InternalTableEntry[aggregateIdentifier=%s, aggregateOwnerIdentifier=%s, name=%s, modificationNumber=%s, isSynchronized=%s, properties=%s]",
			getAggregateIdentifier(),
			getAggregateOwnerIdentifier(), getName(),
			getModificationNumber(), isSynchronized(),
			getProperties());
    }

    public static InternalTableEntry fromEntity(Entity entity)
	    throws ODKDatastoreException {
	return new InternalTableEntry(entity);
    }
}