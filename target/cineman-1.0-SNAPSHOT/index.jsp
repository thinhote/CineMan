<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<html><body style="font-family:sans-serif">--%>
<%--<h2>CineMan</h2>--%>
<%--<ul>--%>
<%--    <li><a href="search-movie">Module 1: Search Movie</a></li>--%>
<%--    <li><a href="revenue">Module 2: Revenue</a></li>--%>
<%--</ul>--%>
<%--</body></html>--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>CineMan</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        :root{
            --bg: #0d1526;
            --panel: #1a2536;     /* màu thẻ */
            --ring: rgba(255,255,255,.08);
            --text: #e7eefb;
            --muted:#9fb0d0;
            --cyan:#22d3ee;
            --cyan-dark:#14b8c5;
        }
        html,body{height:100%;}
        body{
            background: radial-gradient(1200px 700px at 20% -10%, #1a2550 0%, transparent 60%),
            radial-gradient(900px 600px at 100% 0%, #13224a 0%, transparent 60%),
            var(--bg);
            color: var(--text);
            font-family: system-ui,-apple-system,Segoe UI,Roboto,Helvetica,Arial,sans-serif;
            display:flex; align-items:center; justify-content:center;
            padding: 24px;
        }
        .card-box{
            width: 100%;
            max-width: 820px;
            background: var(--panel);
            border-radius: 24px;
            border: 1px solid var(--ring);
            box-shadow: 0 18px 40px rgba(0,0,0,.45);
            padding: 28px 32px;
            text-align: center;
        }
        .brand{
            font-size: 2.2rem;
            font-weight: 800;
            letter-spacing:.2px;
            margin-bottom: 14px;
        }
        .btn-primary-cyan{
            background: var(--cyan);
            color:#07222a;
            font-weight: 700;
            border:none;
            padding: 10px 18px;
            border-radius: 12px;
        }
        .btn-primary-cyan:hover{
            background: var(--cyan-dark);
            color:#042026;
        }
    </style>
</head>
<body>

<div class="card-box">
    <div class="brand">CineMan</div>

    <a href="${pageContext.request.contextPath}/search-movie"
       class="btn btn-primary-cyan">
        Tìm kiếm phim
    </a>
    <br>
    <br>
    <a href="${pageContext.request.contextPath}/revenue"
       class="btn btn-primary-cyan btn-spacing">
        Thống kê doanh thu
    </a>
</div>

</body>
</html>
