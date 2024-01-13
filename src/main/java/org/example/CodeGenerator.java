package org.example;

public class CodeGenerator {
    private static final String nodeTemplate = """
            [%s]""";

    private static final String nodeWithChildTemplate = """
            [%s
            %s
            %s
            ]""";

    private static final String rootTemplate = """
            [%s
            %s
            %s
            ]
            """;

    private static final String forestTemplate = """
            \\begin{forest}
              for tree={
                minimum size=2em,
                edge={-},
                s sep+=10mm
              }
            %s\\end{forest}
            """;

    public static String generate(String[][] nodes) {

        for (int i = 0; i < nodes[nodes.length - 1].length; i++) {
            if (nodes[nodes.length - 1][i] == null) continue;
            nodes[nodes.length - 1][i] = String.format(nodeTemplate, nodes[nodes.length - 1][i]);
        }

        for (int i = nodes.length - 2; i > 0; i--) {
            for (int j = 0; j < nodes[i].length; j++) {
                String leftChildVal = nodes[i + 1][2 * j];
                String rightChildVal = nodes[i + 1][2 * j + 1];
                String leftChild = leftChildVal != null ? indent(nodes[i + 1][2 * j], i) : "";
                String rightChild = rightChildVal != null ? indent(nodes[i + 1][2 * j + 1], i) : "";
                nodes[i][j] = String.format(nodeWithChildTemplate, nodes[i][j], leftChild, rightChild);
            }
        }


        String root = String.format(rootTemplate, nodes[0][0], indent(nodes[1][0], 1), indent(nodes[1][1], 1)).replaceAll("(?m)^", "  ");
        root = root.replaceAll("(?m)^[\s]*\n", "");
        return String.format(forestTemplate, root);
    }

    private static String indent(String text, int level) {
        return text.replaceAll("(?m)^", "  ");
    }
}
