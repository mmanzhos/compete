#include <bits/stdc++.h>

#define REP(i, a, b) for (int i = int(a); i <= int(b); i++)
#define FOR(i, a, b) for (int i = int(a); i < int(b); i++)

using namespace std;

typedef pair<int, int> ii;
typedef pair<char, char> cc;
typedef deque<cc> st;

struct comp{
    bool operator()(ii const& lhs, ii const& rhs){
        return lhs.first < rhs.first;
    }
};

bool fits(const pair<char, char> p1, const pair<char, char> &p2) {
    return p1.first == p2.first || p1.second == p2.second;
}

bool checkStepsBack(list<st> &l, list<st>::iterator &it, int steps) {
    cc p = it->back();
    list<st>::iterator tmp3 = it;
    while (steps > 0 && it != l.begin()) {
        steps--;
        it--;
    }
    if (steps == 0) {
        if (fits(p, it->back())) {
            it->push_back(p);
            tmp3->pop_back();
            if (tmp3->empty()) l.erase(tmp3);
            return true;
        }
    }
    it = tmp3;
    return false;
}

int main() {

    //ifstream cin("input.txt");
    //ofstream cout("output.txt");

    string card;
    while (cin >> card) {
        if (card == "#") break;
        
        list<st> l;
        FOR(i, 0, 52) {
            st s;
            s = {{card[0], card[1]}};
            l.push_back(s);
            for (auto it = l.begin(); it != l.end(); it++) {
                while (true) {
                    if (!checkStepsBack(l, it, 3) && !checkStepsBack(l, it, 1)) {
                        break;
                    }
                }
            }
            if (i != 51)
                cin >> card;
        }

        cout << l.size() << ((l.size() == 1) ? " pile" : " piles") << " remaining:";
        for (auto el : l) {
            cout << " " << el.size();
        }
        cout << endl;
    }

    return 0;
}