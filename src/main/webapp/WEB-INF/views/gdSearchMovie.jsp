<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>CineMan - Tìm kiếm phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        :root{
            --bg: #0d1526;           /* nền ngoài */
            --panel: #121b2c;        /* khung lớn */
            --panel-2: #0f1726;      /* item */
            --muted: #9fb0d0;
            --text: #e7eefb;
            --cyan: #22d3ee;         /* màu nút */
            --cyan-dark:#14b8c5;
            --ring: rgba(255,255,255,.08);
        }

        html,body{height:100%}
        body{
            background: radial-gradient(1200px 700px at 20% -10%, #1a2550 0%, transparent 60%),
            radial-gradient(900px 600px at 100% 0%, #13224a 0%, transparent 60%),
            var(--bg);
            color: var(--text);
        }

        .panel{
            background: var(--panel);
            border: 1px solid var(--ring);
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0,0,0,.4);
        }

        .header-row{
            display:flex; align-items:center; justify-content:space-between;
        }

        .back-link{
            color: var(--muted); text-decoration:none;
            font-size: 1.4rem;
            line-height: 1.3;
        }
        .back-link:hover{ color: var(--text); }

        .form-control{
            background: #0b1324; color: var(--text);
            border: 1px solid var(--ring);
        }
        .form-control::placeholder{ color:#6e7fa6; }

        .btn-search{
            background: var(--cyan);
            color:#07222a; font-weight: 600; border: none;
        }
        .btn-search:hover{ background: var(--cyan-dark); color:#052127; }

        .movie-item{
            background: var(--panel-2);
            border: 1px solid var(--ring);
            border-radius: 16px;
            padding: 16px 18px;
        }
        .movie-title{
            font-weight: 700; color: var(--text);
            font-size: 2.5rem;
            line-height: 1.3;
        }
        .movie-meta{
            color: var(--muted);
        }
        .btn-choose{
            background: var(--cyan); color:#06242a; font-weight: 700; border: none;
            padding: 8px 16px; border-radius: 12px;
        }
        .btn-choose:hover{ background: var(--cyan-dark); color:#031c1f; }

        .divider{
            height:1px; background: var(--ring); margin: 16px 0;
        }
    </style>
</head>

<body class="py-4">
<div class="container">
    <div class="panel p-4 p-md-5">
        <div class="header-row mb-3">
            <h2 class="m-0">Tìm kiếm phim</h2>
            <a class="back-link" href="${pageContext.request.contextPath}/">&larr; CineMan</a>
        </div>

        <div class="mb-2 text-muted">Từ khóa</div>
        <form method="get" action="${pageContext.request.contextPath}/search-movie" class="row g-2 align-items-center">
            <div class="col-12 col-md">
                <input name="q" class="form-control form-control-lg"
                       placeholder="Nhập tên phim, đạo diễn, diễn viên..."
                       value="${param.q}"/>
            </div>
            <div class="col-auto">
                <button class="btn btn-search btn-lg px-4">Tìm kiếm</button>
            </div>
        </form>

        <div class="divider"></div>

        <c:if test="${not empty movies}">
            <div class="d-flex flex-column gap-3">
                <c:forEach var="m" items="${movies}">
                    <div class="movie-item d-flex justify-content-between align-items-center">
                        <div class="me-3">
                            <div class="movie-title">${m.title}</div>
                            <div class="movie-meta">Thể loại: ${m.genre} • ${m.duration} phút</div>
                        </div>
                        <div>
                            <form method="get" action="${pageContext.request.contextPath}/search-movie" class="m-0">
                                <input type="hidden" name="movieId" value="${m.movieId}"/>
                                <button type="submit" class="btn btn-choose">Chọn phim</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty movies && not empty param.q}">
            <div class="text-muted fst-italic">Không tìm thấy phim phù hợp.</div>
        </c:if>
    </div>
</div>
</body>
</html>
