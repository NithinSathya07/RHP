//https://atcoder.jp/contests/abc477/tasks/abc477_d
//https://atcoder.jp/contests/abc477/submissions/79879712
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    if (!(cin >> n >> q)) return 0;

    vector<int> t(q + 1), x(q + 1), L(q + 1, 0);
    vector<char> c(q + 1);
    vector<vector<int>> g(n + 1);

    for (int i = 1; i <= q; ++i) {
        cin >> t[i];
        if (t[i] == 1) {
            cin >> x[i];
            g[x[i]].push_back(i);
            L[i] = L[i - 1];
        } else {
            cin >> c[i];
            L[i] = i;
        }
    }

    string ans = "";

    for (int i = 1; i <= n; ++i) {
        int best = 0;
        int m = g[i].size();

        if (m == 0) {
            best = max(best, L[q]);
        } else {
            if (g[i][0] > 1) {
                best = max(best, L[g[i][0] - 1]);
            }

            for (int j = 1; j < m; j += 2) {
                int l = g[i][j] + 1;
                int r = (j + 1 < m) ? (g[i][j + 1] - 1) : q;

                if (l <= r) {
                    int k = L[r];
                    if (k >= l) {
                        best = max(best, k);
                    }
                }
            }
        }

        if (best == 0) {
            ans += 'a';
        } else {
            ans += c[best];
        }
    }

    cout << ans << "\n";

    return 0;
}