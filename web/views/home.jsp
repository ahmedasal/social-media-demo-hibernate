<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Home</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1> Hello, <c:out value="${currentUser.firstname}"></c:out></h1>--%>
<%--<br>--%>

<%--<div>--%>
<%--    <p>Write Your Post</p>--%>
<%--    <form method="post" action="wall">--%>
<%--        <textarea name="postText"></textarea><br>--%>
<%--        <input type="submit" value="Post">--%>
<%--    </form>--%>
<%--    <h3><c:out value="${postAdded}"></c:out></h3>--%>
<%--</div>--%>

<%--<c:forEach items="${posts}" var="post">--%>

<%--    <div>--%>
<%--        <p style="font-size: 18px;">${post.post}</p>--%>
<%--        <c:if test="${!post.likedByMe}">--%>
<%--            <form method="post" action="like">--%>
<%--                <input type="hidden" name="id" value="${post.id}"/>--%>
<%--                <input type="hidden" name="operation" value="like"/>--%>
<%--                <input type="submit" value="Like">--%>
<%--            </form>--%>
<%--        </c:if>--%>
<%--        <c:if test="${post.likedByMe}">--%>
<%--            <form method="post" action="like">--%>
<%--                <input type="hidden" name="id" value="${post.id}"/>--%>
<%--                <input type="hidden" name="operation" value="unlike"/>--%>
<%--                <input type="submit" value="Unlike">--%>
<%--            </form>--%>
<%--        </c:if>--%>
<%--        <h6><c:out value="${post.likesCount}"></c:out> likes </h6>--%>
<%--        <c:if test="${post.id == postId}">--%>
<%--            <h3><c:out value="${commentAdded}"></c:out></h3>--%>
<%--        </c:if>--%>

<%--        <c:forEach items="${post.comments}" var="comment">--%>
<%--            <p style="font-size: 14px;">${comment.commentText}</p>--%>
<%--        </c:forEach>--%>
<%--        <form method="post" action="comment">--%>
<%--            <input type="hidden" name="id" value="${post.id}"/>--%>
<%--            <textarea name="commentText"></textarea><br>--%>
<%--            <input type="submit" value="Comment">--%>
<%--        </form>--%>
<%--    </div>--%>
<%--    <hr>--%>
<%--</c:forEach>--%>


<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="static/style.css">

    <!------------------LIght BOx for Gallery-------------->
    <link rel="stylesheet" href="static/lightbox.min.css">
    <script type="text/javascript" src="static/lightbox-plus-jquery.min.js"></script>
    <!------------------LIght BOx for Gallery-------------->
    <title>Ahmed Social</title>
</head>
<body>


<!-------------------------------NAvigation Starts------------------>

<nav class="navbar navbar-expand-md navbar-dark mb-4" style="background-color:#3097D1">
    <a href="index.html" class="navbar-brand"><img src="static/img/brand-white.png" alt="logo" class="img-fluid"
                                                   width="80px" height="100px"></a>

    <button class="navbar-toggler" data-toggle="collapse" data-target="#responsive"><span
            class="navbar-toggler-icon"></span></button>


    <div class="collapse navbar-collapse" id="responsive">
        <ul class="navbar-nav mr-auto text-capitalize">
            <li class="nav-item"><a href="wall" class="nav-link active">home</a></li>
            <li class="nav-item"><a href="profile.html" class="nav-link">profile</a></li>
            <li class="nav-item"><a href="#modalview" class="nav-link" data-toggle="modal">messages</a></li>
            <li class="nav-item"><a href="notification.html" class="nav-link">docs</a></li>
            <li class="nav-item"><a href="#" class="nav-link d-md-none">growl</a></li>
            <li class="nav-item"><a href="#" class="nav-link d-md-none">logout</a></li>

        </ul>

        <form action="" class="form-inline ml-auto d-none d-md-block">
            <input type="text" name="search" id="search" placeholder="Search" class="form-control form-control-sm">
        </form>
        <a href="notification.html" class="text-decoration-none" style="color:#CBE4F2;font-size:22px;"><i
                class="far fa-bell ml-3 d-none d-md-block"></i></a>
        <img src="static/img/avatar-dhg.png" alt="" class="rounded-circle ml-3 d-none d-md-block" width="32px"
             height="32px">


    </div>


</nav>

<!---------------------------------------------Ends navigation------------------------------>

<!---------------------------MOdal Section  satrts------------------->

<div class="modal fade" id="modalview">
    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">

        <div class="modal-content">


            <div class="modal-header">
                <div class="modal-title h4">Messages</div>

                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>


            <div class="modal-body">


                <ul class="list-unstyled">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-dhg.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Jchob Thunder and <strong> 1 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>
                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-fat.jpg" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Mark Otto and <strong> 3 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>


                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-mdo.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Archer andu and <strong> 5 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>

                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-dhg.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Jchob Thunder and <strong> 1 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>
                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-fat.jpg" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Mark Otto and <strong> 3 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>


                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-mdo.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Archer andu and <strong> 5 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>


                    <hr class="my-3">
                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-dhg.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Jchob Thunder and <strong> 1 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>
                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-fat.jpg" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Mark Otto and <strong> 3 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>


                    <hr class="my-3">


                    <a href="#" class="text-decoration-none">
                        <li class="media hover-media">

                            <img src="static/img/avatar-mdo.png" alt="img" width="60px" height="60px"
                                 class="rounded-circle mr-3">

                            <div class="media-body text-dark">
                                <h6 class="media-header">Archer andu and <strong> 5 others</strong></h6>
                                <p class="media-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>

                            </div>

                        </li>
                    </a>


                </ul>


            </div>
        </div>


    </div>


</div>

<!-------------------------------MOdal Ends---------------------------->


<!-------------------------------------------Start Grids layout for lg-xl-3 columns and (xs-lg 1 columns)--------------------------------->


<div class="container">
    <div class="row">


        <!--------------------------left columns  start-->

        <div class="col-12 col-lg-3">

            <div class="left-column">


                <div class="card card-left1 mb-4">
                    <img src="static/img/photo-1455448972184-de647495d428.jpg" alt="" class="card-img-top img-fluid">
                    <div class="card-body text-center ">
                        <img src="static/img/avatar-dhg.png" alt="img" width="120px" height="120px"
                             class="rounded-circle mt-n5">
                        <h5 class="card-title"><c:out value="${currentUser.username}"></c:out></h5>
                        <p class="card-text text-justify mb-2">I wish i was a little bit taller, wish i was a baller,
                            wish i had a girl… also.</p>
                        <ul class="list-unstyled nav justify-content-center">
                            <a href="#" class="text-dark text-decoration-none">
                                <li class="nav-item">Friends <br> <strong>12M</strong></li>
                            </a>
                            <a href="#" class="text-dark text-decoration-none">
                                <li class="nav-item">Enimes <br> <strong>1</strong></li>
                            </a>
                        </ul>

                    </div>


                </div>


                <div class="card shadow-sm card-left2 mb-4">

                    <div class="card-body">

                        <h5 class="mb-3 card-title">About <small><a href="#" class="ml-1">Edit</a></small></h5>

                        <p class="card-text"><i class="fas fa-calendar-week mr-2"></i> go to <a href="choosepage"
                                                                                                class="text-decoration-none">pages</a>
                        </p>

                        <p class="card-text"><i class="fas fa-user-friends mr-2"></i> Become a friend with <a href="#"
                                                                                                              class="text-decoration-none">obama</a>
                        </p>
                        <p class="card-text"><i class="far fa-building mr-2"></i> Work at <a href="#"
                                                                                             class="text-decoration-none">Github</a>
                        </p>
                        <p class="card-text"><i class="fas fa-home mr-2"></i> Live in <a href="#"
                                                                                         class="text-decoration-none">San
                            francisco</a></p>
                        <p class="card-text"><i class="fas fa-map-marker mr-2"></i> From <a href="#"
                                                                                            class="text-decoration-none">Seattle,
                            WA</a></p>


                    </div>


                </div>


                <div class="card shadow-sm card-left3 mb-4">

                    <div class="card-body">
                        <h5 class="card-title">Photos<small class="ml-2"><a href="#">.Edit </a></small></h5>

                        <div class="row">
                            <div class="col-6 p-1">
                                <a href="img/left1.jpg" data-lightbox="id"><img src="static/img/left1.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>
                                <a href="img/left2.jpg" data-lightbox="id"><img src="static/img/left2.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>
                                <a href="img/left3.jpg" data-lightbox="id"><img src="static/img/left3.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>

                            </div>


                            <div class="col-6 p-1">
                                <a href="img/left4.jpg" data-lightbox="id"><img src="static/img/left4.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>
                                <a href="img/left5.jpg" data-lightbox="id"><img src="static/img/left5.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>
                                <a href="img/left6.jpg" data-lightbox="id"><img src="static/img/left6.jpg" alt="img"
                                                                                class="img-fluid my-2"></a>

                            </div>

                        </div>

                    </div>


                </div>


            </div>


        </div>


        <!--------------------------Ends Left columns-->


        <!---------------------------------------Middle columns  start---------------->


        <div class="col-12 col-lg-6">


            <div class="middle-column">


                <div class="card">


                    <div class="card-header bg-transparent">
                        <form class="form-inline" method="post" action="wall" enctype="multipart/form-data">


                            <div class="input-group w-100">

                                <textarea name="postText" id="postText" placeholder="Enter your post"
                                          class="form-control form-control-md"></textarea>
                            </div>
                            <div>

                                <br>

                                <input type="file" name="photos" multiple>
                                <br>
                                <input type="submit" value="Post">
                            </div>
                        </form>

                    </div>
                    <c:if test="${post.id eq pId}" >
                        <c:out value="${upload}"></c:out>
                    </c:if>

                    <%--                                <div class="input-group-append">--%>
                    <%--                                    <div class="input-group-text">--%>
                    <%--                                        <i class="fas fa-camera"></i>--%>
                    <%--                                    </div>--%>
                </div>


                </form>



                <!-- ahmed post -->
                <c:forEach items="${posts}" var="post">
                    <div class="card-body">
                        <div class="media">
                            <div class="media-body">
                                <h5><c:out value="${post.username}"></c:out></h5>
                                <p class="card-text text-justify">
                                <h3>${post.post}</h3>
                                </p>
                                <c:forEach items="${post.images}" var="imageId">
                                    <img src="image?id=${imageId}" alt="post" width="500">
                                </c:forEach>
                                    <%--                                    <c:if test="${post.id eq pId}" >--%>
                                    <%--                                        <c:out value="${upload}"></c:out>--%>
                                    <%--                                    </c:if>--%>


                                <div class="clearfix">
                                    <div class="box" class="likeButton">
                                        <c:if test="${!post.likedByMe}">
                                            <form method="post" action="like"
                                                  style="padding-bottom: 3px;padding-left: 3px">
                                                <input type="hidden" name="id" value="${post.id}"/>
                                                <input type="hidden" name="operation" value="like"/>
                                                <input type="submit" value="Like" class="likeButton">
                                            </form>
                                        </c:if>

                                        <c:if test="${post.likedByMe}">
                                            <form method="post" action="like">
                                                <input type="hidden" name="id" value="${post.id}"/>
                                                <input type="hidden" name="operation" value="unlike"/>
                                                <input type="submit" value="Unlike" class="likeButton">
                                            </form>
                                        </c:if>
                                    </div>

                                    <div class="box"
                                         style="padding-bottom: 20px;padding-top: 2px;padding-left: 6px">
                                        likes : <c:out value="${post.likesCount}"></c:out>
                                    </div>
                                </div>


                                    <%--                                <div class="row no-gutters mb-3">--%>
                                    <%--                                    <div class="col-6 p-1 text-center">--%>

                                    <%--                                        <img src="static/img/adventure-alps-clouds-2259810.jpg" alt="" class="img-fluid mb-2">--%>
                                    <%--                                        <img src="static/img/aerial-view-architectural-design-buildings-2228123.jpg" alt="" class="img-fluid">--%>


                                    <%--                                    </div>--%>

                                    <%--                                    <div class="col-6 p-1 text-center">--%>

                                    <%--                                        <img src="static/img/celebration-colored-smoke-dark-2297472.jpg" alt="" class="img-fluid mb-2">--%>
                                    <%--                                        <img src="static/img/bodybuilding-exercise-fitness-2294361.jpg" alt="" class="img-fluid">--%>


                                    <%--                                    </div>--%>


                                    <%--                                </div>--%>

                                <hr>
                                <br>
                                <c:forEach items="${post.comments}" var="comment">
                                    <div class="media mb-3">
                                        <img src="static/img/avatar-dhg.png" alt="img" width="45px" height="45px"
                                             class="rounded-circle mr-2">
                                        <div class="media-body">
                                            <p class="card-text text-justify"><c:out
                                                    value="${comment.user.username}"></c:out>: ${comment.commentText}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <img src="static/img/avatar-dhg.png" alt="img" width="55px" height="55px"
                                 class="rounded-circle mr-3">
                            <small>5min</small>
                        </div>
                    </div>
                    <div class="card-header bg-transparent">
                        <form class="form-inline" method="post" action="comment">


                            <div class="input-group w-100">
                                <input type="hidden" name="id" value="${post.id}"/>
                                <textarea name="commentText" id="commentText" placeholder="Enter your comment"
                                          class="form-control form-control-md"></textarea>
                                <input type="submit" value="Comment">

                                    <%--                                <div class="input-group-append">--%>
                                    <%--                                    <div class="input-group-text">--%>
                                    <%--                                        <i class="fas fa-camera"></i>--%>
                                    <%--                                    </div>--%>
                            </div>


                        </form>

                    </div>

                </c:forEach>
                <dev style=" text-align: center">
                    <c:if test="${currentPage != 1}">
                        <a href="wall?page=${currentPage-1}">prev.</a>
                    </c:if>

                    <c:if test="${(currentPage != 1) and (currentPage != lastPage)}">|</c:if>

                    <c:if test="${currentPage != lastPage}">
                        <a href="wall?page=${currentPage+1}">next</a>
                    </c:if>
                </dev>

            </div>

        </div>


    </div>


    <br> <br> <br><br> <br> <br>

    <!------------------------Middle column Ends---------------->


    <!---------------------------Statrs Right Columns----------------->


    <div class="col-12 col-lg-3">


        <div class="right-column">

            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h6 class="card-title">Sponsored</h6>
                    <img src="static/img/right1.jpg" alt="card-img" class="card-img mb-3">
                    <p class="card-text text-justify"><span class="h6">It might be time to visit Iceland.</span>
                        Iceland is so chill, and everything looks cool here. Also, we heard the people are
                        pretty nice. What are you waiting for?</p>
                    <a href="#" class="btn btn-outline-info card-link btn-sm">Buy a ticket</a>


                </div>

            </div>


            <div class="card shadow-sm mb-4">

                <div class="card-body">

                    <h6 class="card-title ">Likes <a href="#" class="ml-1"><small>.View All</small> </a></h6>
                    <div class="row no-gutters d-none d-lg-flex">
                        <div class="col-6 p-1">
                            <img src="static/img/avatar-dhg.png" alt="img" width="80px" height="80px"
                                 class="rounded-circle mb-4">
                            <img src="static/img/avatar-fat.jpg" alt="img" width="80px" height="80px"
                                 class="rounded-circle">


                        </div>
                        <div class="col-6 p-1 text-left">
                            <h6>Jacob Thornton @fat</h6>
                            <a href="#" class="btn btn-outline-info btn-sm mb-3"><i
                                    class="fas fa-user-friends"></i>Follow </a>

                            <h6>Mark otto</h6>
                            <a href="#" class="btn btn-outline-info  btn-sm"><i class="fas fa-user-friends"></i>Follow
                            </a>

                        </div>

                    </div>

                </div>

                <div class="card-footer">

                    <p class="lead" style="font-size:18px;">Dave really likes these nerds, no one knows why
                        though.</p>
                </div>


            </div>

            <div class="card shadow-sm">
                <div class="card-body">
                    <p>&copy; 2018 Bootstrap


                        <a href="#">About</a>
                        <a href="#">Help</a>
                        <a href="#">Terms</a>
                        <a href="#">Privacy</a>
                        <a href="#">Cookies</a>
                        <a href="#">Ads </a>
                        <a href="#">Info</a>
                        <a href="#">Brand</a>
                        <a href="#">Blog</a>
                        <a href="#">Status</a>
                        <a href="#">Apps</a>
                        <a href="#">Jobs</a>
                        <a href="#">Advertise</a>


                    </p>
                </div>

            </div>


        </div>


    </div>


</div>


<!------------------------Light BOx OPtions------------->
<script>
    lightbox.option({})
</script>


<!------------------------Light BOx OPtions------------->


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</div>
</body>
</html>