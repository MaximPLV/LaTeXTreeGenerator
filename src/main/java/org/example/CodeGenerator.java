package org.example;

public class CodeGenerator {
    private static final String nodeTemplate = """
            [%s]""";

    private static final String nodeWithChildrenTemplate = """
            [%s
            %s
            %s
            ]""";

    private static final String forestTemplate = """
            ----------------------
            \\begin{forest}
              for tree={
                minimum size=2em,
                edge={-},
                s sep+=10mm
              }
            %s
            \\end{forest}
            ----------------------
            """;

    public static String generate(GraphNode root) {
        String texTree = String.format(forestTemplate, generateTree(root));
        return deleteEmptyLines(texTree);
    }

    //currently only works with binary trees, but is theoretically extendable to multiple children
    public static String generateTree(GraphNode root) {
        if (root == null) return "";
        String texTree = root.isLeaf() ?
                String.format(nodeTemplate, root.getText()) :
                String.format(
                        nodeWithChildrenTemplate,
                        root.getText(),
                        generateTree(root.getChildren().get(0)),
                        generateTree(root.getChildren().get(1))
                );
        return indent(texTree);
    }

    private static String indent(String text) {
        return text.replaceAll("(?m)^", "  ");
    }

    private static String deleteEmptyLines(String texTree) {
        return texTree.replaceAll("(?m)^[ \t]*\n", ""); //(?m) for multiline mode
    }
}
