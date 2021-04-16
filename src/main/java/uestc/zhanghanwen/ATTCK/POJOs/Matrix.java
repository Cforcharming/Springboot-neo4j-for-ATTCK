package uestc.zhanghanwen.ATTCK.POJOs;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.NoArgsConstructor;

/**
 * This is the class Matrix mitre GraphNode objects.<br>
 * The params inside the Matrix is the same from its super.
 *
 * @see GraphNode
 * @author zhanghanwen
 * @version 1.0
 */
@NodeEntity(label = "matrix")
@NoArgsConstructor
public class Matrix extends GraphNode {}
