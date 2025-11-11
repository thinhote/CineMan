<%--
  Created by IntelliJ IDEA.
  User: DANG THINH
  Date: 11/2/2025
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Chi tiết giao dịch</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <style>
        :root {
            --bg: #0d1526;
            --panel: #121b2c;
            --panel-dark: #0f1726;
            --ring: rgba(255,255,255,.08);
            --text: #e7eefb;
            --muted: #9fb0d0;
            --cyan: #22d3ee;
        }
        body {
            background: radial-gradient(1000px 600px at 10% 0%, #1a2550 0%, transparent 70%),
            var(--bg);
            color: var(--text);
            font-family: system-ui, -apple-system, Segoe UI, Roboto, Helvetica, Arial, sans-serif;
            padding: 2rem;
        }
        .panel {
            background: var(--panel);
            border-radius: 20px;
            border: 1px solid var(--ring);
            padding: 2rem;
        }
        .title-center {
            text-align: center;
            width: 100%;
            margin-bottom: 20px;
        }
        table {
            color: var(--text);
            text-align: center;
        }
        .table {
            --bs-table-bg: transparent;                /* bỏ nền trắng */
            --bs-table-border-color: rgba(255,255,255,.08);
            --bs-table-color: var(--text);
            background-color: transparent;
            color: var(--text);
            margin-bottom: 0;
        }

        .table thead {
            background-color: var(--panel-dark);       /* header tối hơn nhẹ */
            color: var(--muted);
            border-bottom: 1px solid rgba(255,255,255,.08);
        }

        .table tbody tr {
            background-color: var(--panel);            /* từng dòng có màu panel */
            border-color: rgba(255,255,255,.06);
        }

        .table tbody tr:hover {
            background-color: rgba(255,255,255,.05);   /* hiệu ứng hover nhẹ */
        }

        /* bo góc mềm mại cho bảng */
        .table {
            border-radius: 12px;
            overflow: hidden;
        }
        th { color: var(--muted); }
        .back-link {
            color: var(--cyan);
            text-decoration: none;
        }
        .back-link:hover { color: white; }

        .total-box .label {
            color: var(--muted);
            font-size: .85rem;
        }

        .total-box .value {
            color: var(--cyan);
            font-size: 2.0rem;
            font-weight: 700;
        }

        .info-box {
            background: var(--panel-dark);
            border: 1px solid var(--ring);
            border-radius: 16px;
            padding: 14px 22px;
            box-shadow: 0 4px 18px rgba(0,0,0,.3);
            min-width: 240px;
        }

        .info-box .label {
            color: var(--muted);
            font-size: .9rem;
            margin-bottom: 4px;
        }

        .info-box .value {
            color: var(--text);
            font-weight: 700;
            font-size: 2.0rem;
            letter-spacing: 0.3px;
        }

        .info-box.text-end .value {
            color: var(--cyan); /* Tổng giao dịch nổi bật hơn */
        }
        @media (max-width: 768px) {
            .info-box {
                flex: 1 1 100%;
                text-align: center !important;
            }
        }

    </style>
</head>
<body>
<div class="container">
    <div class="panel">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="fw-bold">Thông tin chi tiết</h2>
            <a href="${pageContext.request.contextPath}/list-customers" class="back-link">&larr; Trở về</a>
        </div>

        <!-- Header -->
        <div class="d-flex justify-content-between align-items-start flex-wrap gap-3 mb-4">
            <!-- Khách hàng -->
            <div class="info-box">
                <div class="label">Khách hàng</div>
                <div class="value">${customerName}</div>
            </div>

            <!-- Tổng giao dịch -->
            <div class="info-box text-end">
                <div class="label">Tổng chi tiêu</div>
                <div class="value">
                    <fmt:formatNumber value="${totalAmount}" type="number" groupingUsed="true" /> VNĐ
                </div>
            </div>
        </div>

        <table class="table table-borderless">
            <thead>
            <tr>
                <th>Ngày thanh toán</th>
                <th>Thời gian</th>
                <th>Mã đơn</th>
                <th>Số tiền</th>
                <th>Phương thức</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${txRows}">
                <tr>
                    <td>${r.date}</td>
                    <td>${r.time}</td>
                    <td>${r.orderCode}</td>
                    <td>
                        <fmt:formatNumber value="${r.amount}" type="number" groupingUsed="true"/>
                    </td>
                    <td>${r.method}</td>
                </tr>
            </c:forEach>

            <c:if test="${empty txRows}">
                <tr>
                    <td colspan="5" class="text-muted fst-italic">
                        Khách hàng chưa có giao dịch nào trong khoảng thời gian này.
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>


