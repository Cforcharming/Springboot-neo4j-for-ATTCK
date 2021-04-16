package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Tactic mitre GraphNode objects.<br>
 * The params inside the Tactic is the same from its super.
 *
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 */
@NodeEntity(label = "tactic")
@NoArgsConstructor
public class Tactic extends GraphNode {}
