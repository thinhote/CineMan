<%--
  Created by IntelliJ IDEA.
  User: DANG THINH
  Date: 11/8/2025
  Time: 9:43 PM
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
    <title>Thống kê theo Khách hàng</title>
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
            background: var(--cyan);
            color:#06242a;
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

        .table.customer-table{
            --bs-table-bg: transparent;
            --bs-table-border-color: rgba(255,255,255,.08);
            --bs-table-color: var(--text);
            margin-bottom: 0;
        }
        .table.customer-table thead{
            background-color: var(--panel-dark);
            color: var(--muted);
            border-bottom: 1px solid rgba(255,255,255,.08);
        }
        .table.customer-table tbody tr{
            background-color: var(--panel);
            border-color: rgba(255,255,255,.06);
        }
        .table.customer-table tbody tr:hover{
            background-color: rgba(255,255,255,.06);
        }
        .table.customer-table th:nth-child(2),
        .table.customer-table th:nth-child(3),
        .table.customer-table th:nth-child(4),
        .table.customer-table td:nth-child(2),
        .table.customer-table td:nth-child(3),
        .table.customer-table td:nth-child(4) {
            text-align: center;
            vertical-align: middle;
        }

        .stats-box table{
            border-radius: 12px;
            overflow: hidden;
        }

        .btn-detail{
            background: var(--cyan);
            color:#06242a;
            font-weight: 600;
            border:none;
            border-radius: 999px;
            padding: 6px 16px;
        }
        .btn-detail:hover{
            background: var(--cyan-dark);
            color:#03181b;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="panel p-4 p-md-5">

        <!-- Header -->
        <div class="hdr-row mb-3">
            <h2 class="title">Thống kê theo Khách hàng</h2>
            <a href="${pageContext.request.contextPath}/revenue" class="back-link">&larr; Trở về</a>
        </div>

        <!-- Tabs / range -->
        <div class="d-flex flex-wrap align-items-center gap-2 mb-3">
<%--            <button type="button" class="tab-btn">Chọn khoảng ngày</button>--%>
            <div class="ms-auto range-pill">
                Khoảng ngày:
                <c:choose>
                    <c:when test="${not empty start && not empty end}">
                        ${start} – ${end}
                    </c:when>
                    <c:otherwise>Chưa chọn</c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- Form chọn khoảng ngày -->
        <form method="get"
              action="${pageContext.request.contextPath}/list-customers"
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
                    Xem thống kê
                </button>
            </div>
        </form>

        <!-- Tổng quan + bảng -->
        <div class="stats-box p-3 p-md-4">
            <div class="stats-label mb-1">Tổng doanh thu (Việt Nam đồng)</div>
            <div class="stats-value mb-3">
                <fmt:formatNumber value="${totalRevenue}" type="number" groupingUsed="true" />
            </div>

            <table class="table customer-table">
                <thead>
                <tr>
                    <th>Khách hàng</th>
                    <th>Số giao dịch</th>
                    <th>Tổng chi tiêu</th>
                    <th>Ngày giao dịch gần nhất</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${customerRows}">
                    <tr>
                        <td>${row.name}</td>
                        <td>${row.orders}</td>
                        <td>
                            <fmt:formatNumber value="${row.amount}" type="number" groupingUsed="true" />
                        </td>
                        <td>
                            <fmt:formatDate value="${row.lastDate}" pattern="dd-MM-yyyy" />
                        </td>
                        <td class="text-end">
                            <a class="btn-detail"
<%--                               href="${pageContext.request.contextPath}/customer-transactions?cid=${row.customerId}&start=${start}&end=${end}">--%>
                               href="${pageContext.request.contextPath}/customer-transactions?cid=${row.customerId}">
                                Xem chi tiết
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty customerRows}">
                    <tr>
                        <td colspan="4" class="text-muted fst-italic">
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
