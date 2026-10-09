//https://atcoder.jp/contests/abc434/tasks/abc434_d
//https://atcoder.jp/contests/abc434/submissions/79879723
#include <iostream>
#include <vector>

using namespace std;

void solve(){
    int N, SS = 2002; 
    if (!(cin >> N)) return;

    vector<int> U(N), D(N), L(N), R(N);
    vector<vector<int>> sky(SS, vector<int>(SS, 0));

    for (int i = 0; i < N; i++){
        cin >> U[i] >> D[i] >> L[i] >> R[i];
        sky[U[i]][L[i]]++;
        sky[U[i]][R[i] + 1]--;
        sky[D[i] + 1][L[i]]--;
        sky[D[i] + 1][R[i] + 1]++;
    }

    for (int row = 1; row < SS; row++){
        for (int col = 1; col < SS; col++){
            sky[row][col] += sky[row][col - 1];
        }
    }
    for (int col = 1; col < SS; col++){
        for (int row = 1; row < SS; row++){
            sky[row][col] += sky[row - 1][col];
        }
    }

    vector<vector<int>> dp(SS, vector<int>(SS, 0));
    int zc = 0;

    for (int row = 1; row <= 2000; row++){
        for (int col = 1; col <= 2000; col++){
            int is_one = (sky[row][col] == 1) ? 1 : 0;
            if (sky[row][col] == 0) zc++;

            dp[row][col] = is_one + dp[row - 1][col] + dp[row][col - 1] - dp[row - 1][col - 1];
        }
    }

    for (int i = 0; i < N; i++){
        int count_only_i = dp[D[i]][R[i]] 
                         - dp[U[i] - 1][R[i]] 
                         - dp[D[i]][L[i] - 1] 
                         + dp[U[i] - 1][L[i] - 1];
                         
        cout << zc + count_only_i << "\n";
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}