package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Mitigation mitre GraphNode objects.<br>
 * The params inside the Mitigation is the same from its super.
 *
 * @author zhanghanwen
 * @see GraphNode
 * @version 1.0
 */
@NodeEntity(label = "mitigation")
@NoArgsConstructor
public class Mitigation extends GraphNode {}
