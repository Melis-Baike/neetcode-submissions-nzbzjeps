class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];

        int components = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                dfs(graph, visited, i);
                components++;
            }
        }
        return components;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        for(int nei : graph.get(node)) {
            if(!visited[nei]) {
                dfs(graph, visited, nei);
            }
        }
    }
}
