package opentree.synthesis;

import java.util.ArrayList;

import opentree.constants.GeneralConstants;
import opentree.constants.RelType;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.traversal.BranchState;

/**
 * Used to traverse the draft tree. Not sure what is meant by the request for a <STATE> parameter for PathExpander interface.
 * If this becomes clear then we should probably provide it.
 * @author cody
 *
 */
public class DraftTreePathExpander implements PathExpander {
	
	Direction direction;
	
	public DraftTreePathExpander(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Iterable<Relationship> expand(Path arg0, BranchState arg1) {
		ArrayList<Relationship> rels = new ArrayList<Relationship>();
		for (Relationship rel : arg0.endNode().getRelationships(direction, RelType.SYNTHCHILDOF)) {
			if (rel.hasProperty("name")) {
				if (String.valueOf(rel.getProperty("name")).equals(GeneralConstants.DRAFT_TREE_NAME.value)) {
					rels.add(rel);
				}
			}
		}
		return rels;
	}

	@Override
	public PathExpander reverse() {
		throw new java.lang.UnsupportedOperationException("reverse method not supported for draft tree expander");
	}

}
