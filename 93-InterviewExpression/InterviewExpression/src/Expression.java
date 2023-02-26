
public class Expression {
public static class ExpressionNode {
	Operation operation;
	Operand operand;
	ExpressionNode left;
	ExpressionNode right;
	
}
ExpressionNode root;
public Object computeExpression() {
	return computeExpressionFromNode(root);
}
private Object computeExpressionFromNode(ExpressionNode node) {
	if (node.operation == null) {
		return node.operand.getValue();
	} else {
		return node.operation.compute(computeExpressionFromNode(node.left),
				computeExpressionFromNode(node.right));
	}
}
}
