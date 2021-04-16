package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Group mitre GraphNode objects.<br>
 * The params inside the Group is the same from its super.
 *
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 */
@NodeEntity(label = "group")
@NoArgsConstructor
public class Group extends GraphNode {}
