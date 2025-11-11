<%--
  Created by IntelliJ IDEA.
  User: DANG THINH
  Date: 11/2/2025
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>Chi tiết phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        :root{
            --bg: #0d1526;
            --panel: #121b2c;
            --ring: rgba(255,255,255,.08);
            --text: #e7eefb;
            --muted: #9fb0d0;
            --accent:#22d3ee;
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
        .hdr{
            display:flex; align-items:center; justify-content:space-between;
        }
        .title{
            font-size: 1.8rem; font-weight: 800; margin: 0;
        }
        .back{
            color: var(--muted); text-decoration: none;
        }
        .back:hover{ color: var(--text); }
        .item-label{
            color: var(--muted);
            min-width: 160px;
        }
        .item-value{
            font-weight: 700;
            color: var(--text);
        }
        .badge-genre{
            background: rgba(34,211,238,.12);
            color: var(--accent);
            border: 1px solid rgba(34,211,238,.35);
            padding: .35rem .6rem;
            border-radius: .6rem;
            font-weight: 700;
        }
        .desc{
            color: var(--text);
        }
        .divider{
            height:1px; background: var(--ring); margin: 18px 0 6px 0;
        }
    </style>
</head>
<body class="py-4">
<div class="container">

    <c:choose>
        <c:when test="${empty movie}">
            <div class="alert alert-warning mt-3">Không tìm thấy dữ liệu phim.</div>
        </c:when>
        <c:otherwise>
            <div class="panel p-4 p-md-5">
                <div class="hdr mb-3">
                    <h2 class="title">Chi tiết phim</h2>
<%--                    <a class="back" href="${pageContext.request.contextPath}/search-movie">← Tìm kiếm</a>--%>
                    <form action="${pageContext.request.contextPath}/search-movie" method="get" class="m-0">
                        <button type="submit" class="btn btn-link p-0 back">← Tìm kiếm</button>
                    </form>

                </div>

                <!-- Thông tin cơ bản -->
                <div class="mb-3">
                    <div class="d-flex mb-2">
                        <div class="item-label">Tiêu đề</div>
                        <div class="item-value">${movie.title}</div>
                    </div>

                    <div class="d-flex mb-2">
                        <div class="item-label">Năm phát hành</div>
                        <div class="item-value">
                            <c:out value="${movie.year}" default="–"/>
                        </div>
                    </div>

                    <div class="d-flex mb-2">
                        <div class="item-label">Thể loại</div>
                        <div class="item-value">
                            <span class="badge-genre">
                                <c:out value="${movie.genre}" default="–"/>
                            </span>
                        </div>
                    </div>

                    <div class="d-flex mb-2">
                        <div class="item-label">Thời lượng</div>
                        <div class="item-value">
                            <c:out value="${movie.duration} phút" default="–"/>
                        </div>
                    </div>

                    <div class="d-flex mb-2">
                        <div class="item-label">Đạo diễn</div>
                        <div class="item-value"><c:out value="${movie.director}" default="–"/></div>
                    </div>

                    <div class="d-flex mb-2">
                        <div class="item-label">Diễn viên</div>
                        <div class="item-value"><c:out value="${movie.cast}" default="–"/></div>
                    </div>
                </div>

                <div class="divider"></div>

                <!-- Mô tả -->
                <p class="desc mt-3 mb-0">
                    <c:out value="${movie.description}" default="Chưa có mô tả."/>
                </p>
            </div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
