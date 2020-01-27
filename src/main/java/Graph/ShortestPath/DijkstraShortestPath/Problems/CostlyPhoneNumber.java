/**
 * 
 * https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/costly-phone-number-december-easy-easy-medium/
 * 
 * 
 * This in an optimization problem, your task is to find the lowest cost to put the required number on the screen. 
Notice, that if you only know, for each digit, the lowest cost of putting this digit on the screen, 
you can easily solve the problem by iterating through digits in required number and summing lowest costs for putting them on the screen. 
Knowing this, we can focus on solving the problem of computing for each letter, 
the lowest cost of putting it to the screen. Let c[d] be that cost for letter d.

One of the possible solutions here is to try to lower costs as long as this is possible. Notice, that we can put digit d on the screen with cost c[d] or putting letters a and b, such that (a + b) % 10 = d, with cost c[a] + c[b] first, and then pressing Add button.

Since initial costs of putting digits on the screen are not greater than 1000, we can simply iterate over all possible pair of digits (a, b) an try to lower the cost of digit (a + b) % 10 as long as it is possible to lower any such cost.

The below pseudocode illustrates this method:

//c[d] is initial cost of putting digit d on the screen

while true:
    reduced = false
    for a = 0 to 9:
        for b = a to 9:
            d = (a + b) % 10
            if c[d] > c[a] + c[b]:
                reduced = true
                c[d] = c[a] + c[b]
    if not reduced:
        break

//all costs have their final lowest values right now
Since each cost can be reduced at most 1000 times and we have 10 such costs, thus in the worst possible case, we will be reducing just one cost iterating over all pairs of digits, so the total approximated number of operations is bounded by 10 * 10 * 1000 * 10 = 106.





const int MAX_COST = 1000;
const int MAX_TEST = 1000;
const int MAX_LEN = 1000;
int main() {
    //freopen("input.in", "r", stdin);
    //freopen("output.out", "w", stdout);
    int T, cost[10], ans, n;
    string s;
    bool ok;
    scanf("%d", &T);
    assert(T <= MAX_TEST && T);
    while (T--) {
        ok = true;
        ans = 0;
        for (int i = 0; i < 10; ++i) {
            scanf("%d", cost + i);
            assert(cost[i] <= MAX_COST);
        }
        while (ok) {
            ok = false;
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < 10; ++j) {
                    if (cost[(i + j) % 10] > cost[i] + cost[j]) {
                        cost[(i + j) % 10] = cost[i] + cost[j];
                        ok = true;
                    }
                }
            }
        }
        scanf("%d", &n);
        cin >> s;
        assert(s.size() == n && n <= MAX_LEN && n);
        for (int i = 0; i < n; ++i) {
            assert(s[i] >= '0' && s[i] <= '9');
            ans += cost[s[i] - '0'];
        }
        printf("%d\n", ans);
    }
    return 0;
}
 */
package Graph.ShortestPath.DijkstraShortestPath.Problems;

import java.util.Scanner;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

public class CostlyPhoneNumber {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int[] input = new int[10];
		int targetNumber;
		int lengthOfTargetNumber;

		Graph<Integer> graph = new Graph<Integer>(false);
		while ((t--) > 0) {

			for (int i = 0; i < 10; ++i) {
				input[i] = sc.nextInt();
				graph.addEdge(10, i, input[i]);
			}
			boolean checkNonRepeat = true;
			Vertex<Integer> vertex = graph.getVertex(10);
			while (checkNonRepeat) {
				checkNonRepeat = false;
				/*
				 * for (int i = 0; i < 10; ++i) { for (int j = 0; j < 10; ++j) {
				 * vertex = graph.getVertex(10);
				 * if(vertex.getAdjacentVertexes()){
				 * 
				 * } } }
				 */
				for (Edge<Integer> edge : vertex.getEdges()) {
					for (Edge<Integer> edgeInner : vertex.getEdges()) {
						int sumOfTwoVertex = (int) ((getAdjecentVertex(edge, graph.getVertex(10)).getId()
								+ getAdjecentVertex(edgeInner, graph.getVertex(10)).getId()));
						Vertex<Integer> vertexCompare = graph.getVertex(sumOfTwoVertex % 10);
						if ((vertexCompare.getEdges().get(0))
								.getWeight() > ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
										.getWeight())
										+ ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
												.getWeight())) {
							(vertexCompare.getEdges().get(0)).setWeight(
									((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0)).getWeight())
											+ ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
													.getWeight()));
							checkNonRepeat = true;
						}
					}
				}
			}
			
			int lengthOfTarget = sc.nextInt();
			sc.nextLine();
			String Target = sc.nextLine();
			int ans = 0;
			int currentNumber=0;
			for(Character ch : Target.toCharArray()){
				currentNumber=Integer.parseInt(ch.toString());
				ans+=graph.getVertex(currentNumber).getEdges().get(0).getWeight();
			}
			
			System.out.println(ans);
		}
	}
	
	static Vertex<Integer> getAdjecentVertex(Edge<Integer> edge, Vertex<Integer> vertex) {
		return (edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1());
	}
}
