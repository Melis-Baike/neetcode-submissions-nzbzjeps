class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int count = 0;
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
            count++;
        }

        if(count != n - 1) {
            return false;
        }

        boolean[] visited = new boolean[n];
        if(dfs(graph, visited, 0, 0)) {
            return false;
        }

        for(int i = 0; i < visited.length; i++) {
            if(visited[i] == false) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int node, int prev) {
        if(visited[node]) {
            return true;
        }

        visited[node] = true;
        for(int nei : graph.get(node)) {
            if(nei == prev) continue;
            if(dfs(graph, visited, nei, node)) {
                return true;
            }
        }

        return false;
    }
}
