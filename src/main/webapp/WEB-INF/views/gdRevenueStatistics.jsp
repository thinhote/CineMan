<%--
  Created by IntelliJ IDEA.
  User: DANG THINH
  Date: 11/2/2025
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />


<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>Thống kê Doanh thu</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        :root{
            --bg: #0d1526;
            --panel: #121b2c;
            --panel-dark: #0f1726;
            --ring: rgba(255,255,255,.08);
            --text: #e7eefb;
            --muted: #9fb0d0;
            --cyan: #22d3ee;
            --cyan-dark:#14b8c5;
        }
        html,body{height:100%;}
        body{
            background: radial-gradient(1200px 700px at 20% -10%, #1a2550 0%, transparent 60%),
            radial-gradient(900px 600px at 100% 0%, #13224a 0%, transparent 60%),
            var(--bg);
            color: var(--text);
            font-family: system-ui,-apple-system,Segoe UI,Roboto,Helvetica,Arial,sans-serif;
            padding: 24px;
        }
        .panel{
            background: var(--panel);
            border-radius: 20px;
            border: 1px solid var(--ring);
            box-shadow: 0 18px 40px rgba(0,0,0,.45);
        }
        .hdr-row{
            display:flex; align-items:center; justify-content:space-between;
        }
        .title{
            font-size: 1.8rem; font-weight: 800; margin:0;
        }
        .back-link{
            color: var(--muted); text-decoration:none;
        }
        .back-link:hover{ color: var(--text); }

        .tab-btn{
            border-radius: 999px;
            padding: 8px 18px;
            border:none;
            font-weight: 600;
            font-size: .95rem;
            background: var(--panel-dark);
            color: var(--muted);
        }
        .tab-btn.active{
            background: var(--cyan);
            color:#06242a;
        }
        .tab-btn:hover{
            background: var(--cyan-dark);
            color:#03181b;
        }
        .range-pill{
            border-radius: 999px;
            padding: 8px 18px;
            background: var(--panel-dark);
            color: var(--muted);
            font-size: .9rem;
        }

        .stats-box{
            background: var(--panel-dark);
            border-radius: 16px;
            border: 1px solid var(--ring);
        }
        .stats-label{
            color: var(--muted);
            font-size: .9rem;
        }
        .stats-value{
            font-size: 1.1rem;
            font-weight: 700;
        }

        .table.revenue-table {
            --bs-table-bg: transparent;                 /* bỏ nền trắng mặc định */
            --bs-table-border-color: rgba(255,255,255,.08);
            --bs-table-color: var(--text);

            background-color: transparent;
            color: var(--text);
            margin-bottom: 0;
        }

        .table.revenue-table thead {
            background-color: var(--panel-dark);        /* header tối hơn chút */
            color: var(--muted);
            border-bottom: 1px solid rgba(255,255,255,.08);
        }

        .table.revenue-table tbody tr {
            background-color: var(--panel);             /* từng dòng cùng tông với panel */
            border-color: rgba(255,255,255,.06);
        }

        .table.revenue-table tbody tr:hover {
            background-color: rgba(255,255,255,.06);    /* hiệu ứng hover nhẹ */
        }

        /* Bo góc và tránh “rò” nền trắng ở mép bảng */
        .stats-box table {
            border-radius: 12px;
            overflow: hidden;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="panel p-4 p-md-5">

        <!-- Header -->
        <div class="hdr-row mb-3">
            <h2 class="title">Thống kê Doanh thu</h2>
            <a href="${pageContext.request.contextPath}/" class="back-link">&larr; CineMan</a>
        </div>
        <!-- Tabs / buttons -->
        <div class="d-flex flex-wrap align-items-center gap-2 mb-3">
<%--            <button type="button" class="tab-btn active">Chọn khoảng ngày</button>--%>

            <!-- Button tới gdListCustomers.jsp (qua servlet / mapping /list-customers) -->
            <a href="${pageContext.request.contextPath}/list-customers"
               class="tab-btn active">Thống kê KH theo doanh thu</a>

            <div class="ms-auto range-pill">
                Khoảng ngày:
                <c:choose>
<%--                    <c:when test="${not empty start && not empty end}">--%>
<%--                        ${start} – ${end}--%>
<%--&lt;%&ndash;                        <td><fmt:formatDate value="${start} - ${end}" pattern="dd-MM-yyyy" /></td>&ndash;%&gt;--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>chưa chọn</c:otherwise>--%>
                    <c:when test="${not empty start && not empty end}">
                        <c:out value="${startDisplay}" /> – <c:out value="${endDisplay}" />
                    </c:when>
                    <c:otherwise>chưa chọn</c:otherwise>
                </c:choose>
            </div>
        </div>



        <!-- 🔽 Form chọn khoảng ngày -->
        <form method="get"
              action="${pageContext.request.contextPath}/revenue"
              class="row g-2 align-items-end mb-3">
            <div class="col-12 col-sm-4">
                <label class="form-label text-light">Từ ngày</label>
                <input type="date" name="start"
                       class="form-control"
                       value="${start}"/>
            </div>
            <div class="col-12 col-sm-4">
                <label class="form-label text-light">Đến ngày</label>
                <input type="date" name="end"
                       class="form-control"
                       value="${end}"/>
            </div>
            <div class="col-12 col-sm-4">
                <button class="btn btn-primary w-100 mt-2 mt-sm-4"
                        style="background: var(--cyan); border:none; font-weight:600;">
                    Xem doanh thu
                </button>
            </div>
        </form>

        <!-- Tổng quan + bảng -->
        <div class="stats-box p-3 p-md-4">
            <!-- dòng tổng -->
            <div class="row mb-3">
                <div class="col-md-6 mb-2 mb-md-0">
                    <div class="stats-label">Tổng doanh thu (Việt Nam đồng)</div>
                    <div class="stats-value">
<%--                        ${totalRevenue}--%>
                        <fmt:formatNumber value="${totalRevenue}" type="number" groupingUsed="true" />
                    </div>
                </div>
<%--                <div class="col-md-6">--%>
<%--                    <div class="stats-label">Số vé</div>--%>
<%--                    <div class="stats-value">--%>
<%--                        <c:out value="${totalTickets}" default="0"/>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
            <!-- bảng doanh thu theo ngày -->
            <table class="table revenue-table">
                <thead>
                <tr>
                    <th>Ngày</th>
                    <th>Doanh thu</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${revenueByDate}">

                    <tr>
<%--                        <td>${e.key}</td>--%>
                        <td><fmt:formatDate value="${e.key}" pattern="dd-MM-yyyy" /></td>
                        <td><fmt:formatNumber value="${e.value}" type="number" groupingUsed="true" /></td>
                    </tr>
                </c:forEach>

                <c:if test="${empty revenueByDate}">
                    <tr>
                        <td colspan="2" class="text-muted fst-italic">
                            Chưa có dữ liệu trong khoảng ngày được chọn.
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

    </div>
</div>
</body>
</html>
