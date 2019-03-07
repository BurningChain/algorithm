!function (e) {
    var a = window.webpackJsonp;
    window.webpackJsonp = function (n, o, r) {
        for (var d, f, p, s = 0, i = []; s < n.length; s++) f = n[s], c[f] && i.push(c[f][0]), c[f] = 0;
        for (d in o) Object.prototype.hasOwnProperty.call(o, d) && (e[d] = o[d]);
        for (a && a(n, o, r); i.length;) i.shift()();
        if (r) for (s = 0; s < r.length; s++) p = t(t.s = r[s]);
        return p
    };
    var n = {}, c = {34: 0};

    function t(a) {
        if (n[a]) return n[a].exports;
        var c = n[a] = {i: a, l: !1, exports: {}};
        return e[a].call(c.exports, c, c.exports, t), c.l = !0, c.exports
    }

    t.e = function (e) {
        var a = c[e];
        if (0 === a) return new Promise(function (e) {
            e()
        });
        if (a) return a[2];
        var n = new Promise(function (n, t) {
            a = c[e] = [n, t]
        });
        a[2] = n;
        var o = document.getElementsByTagName("head")[0], r = document.createElement("script");
        r.type = "text/javascript", r.charset = "utf-8", r.async = !0, r.timeout = 12e4, t.nc && r.setAttribute("nonce", t.nc), r.src = t.p + "" + ({
            0: "pages/index",
            1: "layouts/default",
            2: "pages/currency/detail",
            3: "pages/currency/_id",
            4: "pages/exchange/detail",
            5: "pages/exchange/index",
            6: "pages/search",
            7: "layouts/footer",
            8: "layouts/mobileFooter",
            9: "pages/app",
            10: "pages/mobile/app",
            11: "pages/social",
            12: "pages/media/index",
            13: "pages/media/_tag",
            14: "pages/announcement/index",
            15: "pages/announcement/detail",
            16: "pages/announcement/_id",
            17: "pages/kline/index",
            18: "pages/media/newsflash",
            19: "pages/aboutus",
            20: "pages/media-cooperate",
            21: "pages/whitepaper",
            22: "pages/support/verifyemail",
            23: "pages/support/resetpassword",
            24: "pages/support/agreement",
            25: "pages/project-include",
            26: "pages/privacy-policy",
            27: "pages/exchange-cooperate",
            28: "pages/ad-cooperate",
            29: "layouts/clear",
            30: "pages/universal-download",
            31: "pages/download-vietnam"
        }[e] || e) + "." + {
            0: "c52af02c2e3dad78ba6e",
            1: "b2c0a10506f614df7937",
            2: "8db0bf60909ef057a756",
            3: "3ded25e401bc16523d9d",
            4: "83f57abaa68119b5100c",
            5: "9aac671c3c43143df671",
            6: "31ab8b4e45fde0cc0cc2",
            7: "3eff0187f2fd56e0e6b7",
            8: "45d62f6682a9779eb756",
            9: "9ddb909d0367319d04c3",
            10: "72a4df13994670d75525",
            11: "93e35581b3e55a50ed5d",
            12: "7015ec8e53a7d568d7d3",
            13: "7840ef8f1c92ea364e4a",
            14: "d78bd061b0f97afae85c",
            15: "fee248240026311799b6",
            16: "ffaa6edb2b310aaccda6",
            17: "b019bac6022e352ddedc",
            18: "e3c5d2b88fb33aeabe05",
            19: "df2ca4dd435db1c745ae",
            20: "1fb20996faa97f311e9c",
            21: "02e5057997feebc9180c",
            22: "20e350c2174f524a8b74",
            23: "229fdc8379d425cf0661",
            24: "30d28fd9aa0ba8cabe54",
            25: "e43e93d6b737ab63929c",
            26: "8c8edf3a31e20f239fd7",
            27: "838d331d3e887e753e6b",
            28: "107defc5b2d715400dae",
            29: "b72dc4dc1a46dac3b56f",
            30: "97552184f4151ba14aa4",
            31: "4478eaf474cff8a7fc6f"
        }[e] + ".js";
        var d = setTimeout(f, 12e4);

        function f() {
            r.onerror = r.onload = null, clearTimeout(d);
            var a = c[e];
            0 !== a && (a && a[1](new Error("Loading chunk " + e + " failed.")), c[e] = void 0)
        }

        return r.onerror = r.onload = f, o.appendChild(r), n
    }, t.m = e, t.c = n, t.d = function (e, a, n) {
        t.o(e, a) || Object.defineProperty(e, a, {configurable: !1, enumerable: !0, get: n})
    }, t.n = function (e) {
        var a = e && e.__esModule ? function () {
            return e.default
        } : function () {
            return e
        };
        return t.d(a, "a", a), a
    }, t.o = function (e, a) {
        return Object.prototype.hasOwnProperty.call(e, a)
    }, t.p = "https://cdn.mytoken.org/", t.oe = function (e) {
        throw console.error(e), e
    }
}([]);