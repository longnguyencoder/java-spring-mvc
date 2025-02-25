<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="" />
                    <meta name="author" content="" />
                    <title>Create Product - SB Admin</title>

                    // upload file lên
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script>
                        $(document).ready(() => {
                            const avatarFile = $("#avatarFile");
                            avatarFile.change(function (e) {
                                const imgURL = URL.createObjectURL(e.target.files[0]);
                                $("#avatarPreview").attr("src", imgURL);
                                $("#avatarPreview").css({ "display": "block" });
                            });
                        });
                    </script>
                    <link href="/css/style.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    // layout header
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage Product</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Product</li>
                                    </ol>
                                    <div class="mt-5">
                                        <div class="row">
                                            <div class="col-md-6 col-12 mx-auto">
                                                <h3>Create a Product</h3>
                                                <hr />
                                                <form:form method="post" action="/admin/product/create"
                                                    modelAttribute="newProduct" class="row"
                                                    enctype="multipart/form-data">
                                                    <!-- name -->
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <c:set var="errorName">
                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Name:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                            path="name" />
                                                        ${errorName}
                                                    </div>
                                                    <!-- giá -->
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <c:set var="errorPrice">
                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Price:</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                                            path="price" />
                                                        ${errorPrice}
                                                    </div>
                                                    <!-- chi tiết sản phẩm -->
                                                    <div class="col-12 col-md-6">
                                                        <c:set var="errorDetail">
                                                            <form:errors path="detailDesc"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Detail description:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorDetail ? 'is-invalid' : ''}"
                                                            path="detailDesc" />
                                                        ${errorDetail}
                                                    </div>
                                                    <!-- short des -->
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <c:set var="errorShort">
                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">short description:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorShort ? 'is-invalid' : ''}"
                                                            path="shortDesc" />
                                                        ${errorShort}
                                                    </div>
                                                    <!-- số lượng sản phẩm -->
                                                    <div class="mb-3 mb-3 col-12 ">
                                                        <c:set var="errorQuantity">
                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Quantity</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                            path="quantity" />
                                                        ${errorQuantity}
                                                    </div>

                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Factory:</label>
                                                        <form:select class="form-select" path="factory">
                                                            <form:option value="ADMIN">Apple(IOS)</form:option>
                                                            <form:option value="DELL">DELL</form:option>
                                                            <form:option value="ASUS">Asus</form:option>
                                                            <form:option value="LG">LG</form:option>
                                                            <form:option value="ACER">Acer</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Target:</label>
                                                        <form:select class="form-select" path="target">
                                                            <form:option value="GAMING">Gaming</form:option>
                                                            <form:option value="SINHVIEN-VANPHONG">Sinh viên - Văn Phòng
                                                            </form:option>
                                                            <form:option value="MONG-NHE">Mỏng nhẹ
                                                            </form:option>
                                                            <form:option value="THIET-KE-DO-HOA">Thiết kế đồ họa
                                                            </form:option>


                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label for="avatarFile" class="form-label">Image:</label>
                                                        <input type="file" id="imageFile" class="form-control"
                                                            accept=".png, .jpg, .jpeg" name="saveProductFile" />
                                                    </div>

                                                    <div class="col-12 col-md-6">
                                                        <img id="avatarPreview" src="" alt="avatar preview"
                                                            style="max-height: 250px; display: none; ">
                                                    </div>
                                                    <div class="col-12 mb-5">
                                                        <button type="submit" class="btn btn-primary">Submit</button>

                                                    </div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="js/scripts.js"></script>

                </body>

                </html>