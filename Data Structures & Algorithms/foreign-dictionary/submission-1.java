class Solution {
    public String foreignDictionary(String[] words) {
        int n = words.length - 1;
        char[][] edges = new char[n][2];

        // build edges
        for(int i = 0; i < n; i++) {
            
            String word = words[i];
            String nextWord = words[i + 1];

            // edge case abc -> ab
            if(word.startsWith(nextWord) && word.length() > nextWord.length()) {
                return "";
            }

            for(int j = 0; j < word.length(); j++) {

                if(j >= nextWord.length()) break;

                if(word.charAt(j) != nextWord.charAt(j)) {
                    edges[i] = new char[]{word.charAt(j), nextWord.charAt(j)};
                    break;
                } 
            }
        }

        // Adjacency list
        Map<Character, List<Character>> graph = new HashMap<>();
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);

        for(String word : words) {
            for(char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                if(indegree[c - 'a'] == -1) indegree[c - 'a'] = 0;
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(edges[i][0] == '\0') continue;
            char from = edges[i][0];
            char to = edges[i][1];

            graph.computeIfAbsent(from, key -> new ArrayList<>()).add(to);

            indegree[to - 'a']++;
        }

        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offer((char)(i + 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();

        int visited = 0;
        while(!queue.isEmpty()) {
            char curr = queue.poll();

            visited++;
            sb.append(curr);

            for(char neighbor : graph.getOrDefault(curr, List.of())) {
                if(--indegree[neighbor - 'a'] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return visited == graph.size() ? sb.toString() : ""; // result
    }
}
