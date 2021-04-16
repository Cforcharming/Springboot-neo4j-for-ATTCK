package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Technique mitre GraphNode objects.<br>
 * Apart from super, it has more params.
 *
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 */
@NodeEntity(label = "technique")
@NoArgsConstructor
public class Technique extends GraphNode {}
