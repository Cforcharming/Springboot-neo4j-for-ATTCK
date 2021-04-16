package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Software mitre GraphNode objects.<br>
 * The params inside the Software is the same from its super.
 *
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 */
@NodeEntity(label = "software")
@NoArgsConstructor
public class Software extends GraphNode {}
