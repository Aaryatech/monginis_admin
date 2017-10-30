


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Example of Bootstrap 3 Accordion</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
</head>
<body>
	<div class="bs-example">
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne">1. What is HTML?</a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
						<input type="text" name="1" value="ppppppppppppppppppppp">
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo">2. What is Bootstrap?</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
						<p>
							Bootstrap is a powerful front-end framework for faster and easier
							web development. It is a collection of CSS and HTML conventions.
							<a
								href="https://www.tutorialrepublic.com/twitter-bootstrap-tutorial/"
								target="_blank">Learn more.</a>
						</p>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseThree">3. What is CSS?</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse">
					<div class="panel-body">
						<p>
							CSS stands for Cascading Style Sheet. CSS allows you to specify
							various style properties for a given HTML element such as colors,
							backgrounds, fonts etc. <a
								href="https://www.tutorialrepublic.com/css-tutorial/"
								target="_blank">Learn more.</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<p>
			<strong>Note:</strong> Click on the linked heading text to expand or
			collapse accordion panels.
		</p>
	</div>
</body>
</html>



<!-- <!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>GRN for gate</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        Place favicon.ico and apple-touch-icon.png in the root directory

        base css styles
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">

        page specific css styles
        <link rel="stylesheet" type="text/css" href="assets/chosen-bootstrap/chosen.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jquery-tags-input/jquery.tagsinput.css" />
        <link rel="stylesheet" type="text/css" href="assets/jquery-pwstrength/jquery.pwstrength.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-fileupload/bootstrap-fileupload.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.css" />
        <link rel="stylesheet" type="text/css" href="assets/dropzone/downloads/css/dropzone.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-colorpicker/css/colorpicker.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-timepicker/compiled/timepicker.css" />
        <link rel="stylesheet" type="text/css" href="assets/clockface/css/clockface.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-switch/static/stylesheets/bootstrap-switch.css" />
        <link rel="stylesheet" type="text/css" href="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
        
        flaty css styles
        <link rel="stylesheet" href="css/flaty.css">
        <link rel="stylesheet" href="css/flaty-responsive.css">

        <link rel="shortcut icon" href="img/favicon.png">
    </head>
    <body>

        BEGIN Theme Setting
        <div id="theme-setting">
            <a href="#"><i class="fa fa-gears fa fa-2x"></i></a>
            <ul>
                <li>
                    <span>Skin</span>
                    <ul class="colors" data-target="body" data-prefix="skin-">
                        <li class="active"><a class="blue" href="#"></a></li>
                        <li><a class="red" href="#"></a></li>
                        <li><a class="green" href="#"></a></li>
                        <li><a class="orange" href="#"></a></li>
                        <li><a class="yellow" href="#"></a></li>
                        <li><a class="pink" href="#"></a></li>
                        <li><a class="magenta" href="#"></a></li>
                        <li><a class="gray" href="#"></a></li>
                        <li><a class="black" href="#"></a></li>
                    </ul>
                </li>
                <li>
                    <span>Navbar</span>
                    <ul class="colors" data-target="#navbar" data-prefix="navbar-">
                        <li class="active"><a class="blue" href="#"></a></li>
                        <li><a class="red" href="#"></a></li>
                        <li><a class="green" href="#"></a></li>
                        <li><a class="orange" href="#"></a></li>
                        <li><a class="yellow" href="#"></a></li>
                        <li><a class="pink" href="#"></a></li>
                        <li><a class="magenta" href="#"></a></li>
                        <li><a class="gray" href="#"></a></li>
                        <li><a class="black" href="#"></a></li>
                    </ul>
                </li>
                <li>
                    <span>Sidebar</span>
                    <ul class="colors" data-target="#main-container" data-prefix="sidebar-">
                        <li class="active"><a class="blue" href="#"></a></li>
                        <li><a class="red" href="#"></a></li>
                        <li><a class="green" href="#"></a></li>
                        <li><a class="orange" href="#"></a></li>
                        <li><a class="yellow" href="#"></a></li>
                        <li><a class="pink" href="#"></a></li>
                        <li><a class="magenta" href="#"></a></li>
                        <li><a class="gray" href="#"></a></li>
                        <li><a class="black" href="#"></a></li>
                    </ul>
                </li>
                <li>
                    <span></span>
                    <a data-target="navbar" href="#"><i class="fa fa-square-o"></i> Fixed Navbar</a>
                    <a class="hidden-inline-xs" data-target="sidebar" href="#"><i class="fa fa-square-o"></i> Fixed Sidebar</a>
                </li>
            </ul>
        </div>
        END Theme Setting

        BEGIN Navbar
        <div id="navbar" class="navbar">
            <button type="button" class="navbar-toggle navbar-btn collapsed" data-toggle="collapse" data-target="#sidebar">
                <span class="fa fa-bars"></span>
            </button>
            <a class="navbar-brand" href="#">
                <small>
                    <i class="fa fa-desktop"></i>
                    FLATY Admin
                </small>
            </a>

            BEGIN Navbar Buttons
            <ul class="nav flaty-nav pull-right">
                BEGIN Button Tasks
                <li class="hidden-xs">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="fa fa-tasks"></i>
                        <span class="badge badge-warning">4</span>
                    </a>

                    BEGIN Tasks Dropdown
                    <ul class="dropdown-navbar dropdown-menu">
                        <li class="nav-header">
                            <i class="fa fa-check"></i>
                            4 Tasks to complete
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Software Update</span>
                                    <span class="pull-right">75%</span>
                                </div>

                                <div class="progress progress-mini">
                                    <div style="width:75%" class="progress-bar progress-bar-warning"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Transfer To New Server</span>
                                    <span class="pull-right">45%</span>
                                </div>

                                <div class="progress progress-mini">
                                    <div style="width:45%" class="progress-bar progress-bar-danger"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Bug Fixes</span>
                                    <span class="pull-right">20%</span>
                                </div>

                                <div class="progress progress-mini">
                                    <div style="width:20%" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Writing Documentation</span>
                                    <span class="pull-right">85%</span>
                                </div>

                                <div class="progress progress-mini progress-striped active">
                                    <div style="width:85%" class="progress-bar progress-bar-success"></div>
                                </div>
                            </a>
                        </li>

                        <li class="more">
                            <a href="#">See tasks with details</a>
                        </li>
                    </ul>
                    END Tasks Dropdown
                </li>
                END Button Tasks

                BEGIN Button Notifications
                <li class="hidden-xs">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="fa fa-bell"></i>
                        <span class="badge badge-important">5</span>
                    </a>

                    BEGIN Notifications Dropdown
                    <ul class="dropdown-navbar dropdown-menu">
                        <li class="nav-header">
                            <i class="fa fa-warning"></i>
                            5 Notifications
                        </li>

                        <li class="notify">
                            <a href="#">
                                <i class="fa fa-comment orange"></i>
                                <p>New Comments</p>
                                <span class="badge badge-warning">4</span>
                            </a>
                        </li>

                        <li class="notify">
                            <a href="#">
                                <i class="fa fa-twitter blue"></i>
                                <p>New Twitter followers</p>
                                <span class="badge badge-info">7</span>
                            </a>
                        </li>

                        <li class="notify">
                            <a href="#">
                                <img src="img/demo/avatar/avatar2.jpg" alt="Alex" />
                                <p>David would like to become moderator.</p>
                            </a>
                        </li>

                        <li class="notify">
                            <a href="#">
                                <i class="fa fa-bug pink"></i>
                                <p>New bug in program!</p>
                            </a>
                        </li>

                        <li class="notify">
                            <a href="#">
                                <i class="fa fa-shopping-cart green"></i>
                                <p>You have some new orders</p>
                                <span class="badge badge-success">+10</span>
                            </a>
                        </li>

                        <li class="more">
                            <a href="#">See all notifications</a>
                        </li>
                    </ul>
                    END Notifications Dropdown
                </li>
                END Button Notifications

                BEGIN Button Messages
                <li class="hidden-xs">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="fa fa-envelope"></i>
                        <span class="badge badge-success">3</span>
                    </a>

                    BEGIN Messages Dropdown
                    <ul class="dropdown-navbar dropdown-menu">
                        <li class="nav-header">
                            <i class="fa fa-comments"></i>
                            3 Messages
                        </li>

                        <li class="msg">
                            <a href="#">
                                <img src="img/demo/avatar/avatar3.jpg" alt="Sarah's Avatar" />
                                <div>
                                    <span class="msg-title">Sarah</span>
                                    <span class="msg-time">
                                        <i class="fa fa-clock-o"></i>
                                        <span>a moment ago</span>
                                    </span>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            </a>
                        </li>

                        <li class="msg">
                            <a href="#">
                                <img src="img/demo/avatar/avatar4.jpg" alt="Emma's Avatar" />
                                <div>
                                    <span class="msg-title">Emma</span>
                                    <span class="msg-time">
                                        <i class="fa fa-clock-o"></i>
                                        <span>2 Days ago</span>
                                    </span>
                                </div>
                                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris ...</p>
                            </a>
                        </li>

                        <li class="msg">
                            <a href="#">
                                <img src="img/demo/avatar/avatar5.jpg" alt="John's Avatar" />
                                <div>
                                    <span class="msg-title">John</span>
                                    <span class="msg-time">
                                        <i class="fa fa-clock-o"></i>
                                        <span>8:24 PM</span>
                                    </span>
                                </div>
                                <p>Duis aute irure dolor in reprehenderit in ...</p>
                            </a>
                        </li>

                        <li class="more">
                            <a href="#">See all messages</a>
                        </li>
                    </ul>
                    END Notifications Dropdown
                </li>
                END Button Messages

                BEGIN Button User
                <li class="user-profile">
                    <a data-toggle="dropdown" href="#" class="user-menu dropdown-toggle">
                        <img class="nav-user-photo" src="img/demo/avatar/avatar1.jpg" alt="Penny's Photo" />
                        <span id="user_info">
                            Penny
                        </span>
                        <i class="fa fa-caret-down"></i>
                    </a>

                    BEGIN User Dropdown
                    <ul class="dropdown-menu dropdown-navbar" id="user_menu">
                        <li class="nav-header">
                            <i class="fa fa-clock-o"></i>
                            Logined From 20:45
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-cog"></i>
                                Account Settings
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-user"></i>
                                Edit Profile
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-question"></i>
                                Help
                            </a>
                        </li>

                        <li class="divider visible-xs"></li>

                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-tasks"></i>
                                Tasks
                                <span class="badge badge-warning">4</span>
                            </a>
                        </li>
                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-bell"></i>
                                Notifications
                                <span class="badge badge-important">8</span>
                            </a>
                        </li>
                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-envelope"></i>
                                Messages
                                <span class="badge badge-success">5</span>
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="fa fa-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                    BEGIN User Dropdown
                </li>
                END Button User
            </ul>
            END Navbar Buttons
        </div>
        END Navbar

        BEGIN Container
        <div class="container" id="main-container">
            BEGIN Sidebar
            <div id="sidebar" class="navbar-collapse collapse">
                BEGIN Navlist
                <ul class="nav nav-list">
                    BEGIN Search Form
                    <li>
                        <form target="#" method="GET" class="search-form">
                            <span class="search-pan">
                                <button type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                                <input type="text" name="search" placeholder="Search ..." autocomplete="off" />
                            </span>
                        </form>
                    </li>
                    END Search Form
                    <li>
                        <a href="index.html">
                            <i class="fa fa-dashboard"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>

                    <li>
                        <a href="typography.html">
                            <i class="fa fa-text-width"></i>
                            <span>Typography</span>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span>UI Elements</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="ui_general.html">General</a></li>
                            <li><a href="ui_button.html">Button</a></li>
                            <li><a href="ui_tabs.html">Tab & Accordion</a></li>
                            <li><a href="ui_slider.html">Slider</a></li>
                            <li><a href="ui_tile.html">Tile</a></li>
                            <li><a href="ui_timeline.html">Timeline</a></li>
                            <li><a href="ui_chart.html">Chart</a></li>
                            <li><a href="ui_message.html">Conversation</a></li>
                            <li><a href="ui_horizontal-menu.html">Horizontal Menu</a></li>
                            <li><a href="ui_icon.html">Icon</a></li>
                        </ul>
                        END Submenu
                    </li>

                    <li class="active">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-edit"></i>
                            <span>Forms</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="form_layout.html">Layout</a></li>
                            <li class="active"><a href="form_component.html">Component</a></li>
                            <li><a href="form_wizard.html">Wizard</a></li>
                            <li><a href="form_validation.html">Validation</a></li>
                             <li><a href="add_gif_picture.html">Add GIF Picture</a></li>
                             <li><a href="manage_gif.html">Manage GIF</a></li>
                             <li><a href="view_details.html">View GIF Details</a></li>
                        </ul>
                        END Submenu
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-list"></i>
                            <span>Tables</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="table_basic.html">Basic</a></li>
                            <li><a href="table_advance.html">Advance</a></li>
                            <li><a href="table_dynamic.html">Dynamic</a></li>
                        </ul>
                        END Submenu
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-globe"></i>
                            <span>Maps</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="map_google.html">Google Maps</a></li>
                            <li><a href="map_vector.html">Vector Maps</a></li>
                        </ul>
                        END Submenu
                    </li>

                    <li>
                        <a href="box.html">
                            <i class="fa fa-list-alt"></i>
                            <span>Box</span>
                        </a>
                    </li>

                    <li>
                        <a href="calendar.html">
                            <i class="fa fa-calendar"></i>
                            <span>Calendar</span>
                        </a>
                    </li>

                    <li>
                        <a href="gallery.html">
                            <i class="fa fa-picture-o"></i>
                            <span>Gallery</span>
                        </a>
                    </li>

                    <li>
                        <a href="grid.html">
                            <i class="fa fa-th"></i>
                            <span>Griding System</span>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-file-text-o"></i>
                            <span>Sample Pages</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="extra_login.html">Login &amp; Register</a></li>
                            <li><span>Email</span>
                                <ul>
                                    <li><a href="extra_mail-list.html">Inbox</a></li>
                                    <li><a href="extra_mail-msg.html">Mail Thread</a></li>
                                    <li><a href="extra_mail-compose.html">Compose</a></li>
                                </ul>
                            </li>
                            <li><a href="extra_profile.html">User Profile</a></li>
                            <li><a href="extra_invoice.html">Invoice</a></li>
                            <li><a href="extra_search.html">Search Results</a></li>
                        </ul>
                        END Submenu
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-file"></i>
                            <span>Other Pages</span>
                            <b class="arrow fa fa-angle-right"></b>
                        </a>

                        BEGIN Submenu
                        <ul class="submenu">
                            <li><a href="more_blank.html">Blank Page</a></li>
                            <li><a href="more_full-width.html">Full Width Page</a></li>
                            <li><span>Errors</span>
                                <ul>
                                    <li><a href="more_error-404.html">Error 404</a></li>
                                    <li><a href="more_error-500.html">Error 500</a></li>
                                </ul>
                            </li>
                            <li><a href="more_set-skin.html">Skin</a></li>
                            <li><a href="more_set-sidebar-navbar-color.html">Sidebar &amp; Navbar</a></li>
                            <li><a href="more_sidebar-collapsed.html">Collapsed Sidebar</a></li>
                        </ul>
                        END Submenu
                    </li>
                </ul>
                END Navlist

                BEGIN Sidebar Collapse Button
                <div id="sidebar-collapse" class="visible-lg">
                    <i class="fa fa-angle-double-left"></i>
                </div>
                END Sidebar Collapse Button
            </div>
            END Sidebar

            BEGIN Content
            <div id="main-content">
                BEGIN Page Title
                <div class="page-title">
                    <div>
                        <h1><i class="fa fa-file-o"></i>GRN for Gate</h1>
                        
                    </div>
                </div>
                END Page Title

                BEGIN Breadcrumb
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="index.html">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Lorel Ipsum</li>
                    </ul>
                </div>
                END Breadcrumb

                BEGIN Main Content    
				<div  class="box box-green">
                            <div class="box-title">
                                <h3><i class="fa fa-list-alt"></i>Bill</h3>
                                
                            </div>
					<div class=" box-content ">
					    <div class="row">
						<div class="col-md-12 table-responsive">
							    <table class="table table-bordered table-striped fill-head " style="width:100%">
										
											<tr>
												<th>Bill NO</th>
												<th>Franchisee Name</th>
												<th>Item</th>
												<th>Type GRN</th>
												<th>QTY</th>
												<th>Action</th>
											</tr>
										
										
											<tr>
												<td>1</td>
												<td>joe d</td>
												<td>cake</td>
												<td>Paid</td>
												<td>5</td>
												<td>
													<button class="btn btn-info">Agree</button>
													<button class="btn btn-info" onclick="disagree()">Disagree</button>
													<button class="btn btn-info" onclick="show()" >Show</button>	
												</td>  
											</tr>
											<tr>
											    <td colspan="6">
												    Disagree collapse
													<div id="disagree_collapse1">
														<div class="panel-body">
															<form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;">Label1</label>		
																	</div>
																	<div class="col-md-3 control-label">
																		<textarea placeholder="respond"class="form-control" rows="3" cols="15"></textarea>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>	
																	</div>
																	
																</div> 
															</form>
														</div>
													</div>
													show collapse
													<div id="show_collapse1">
														<div class="panel-body">
															 <form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label1</label>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label2</label>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>
																	</div>	
																	
																</div> 
															</form>
														</div>
													</div>
												</td>
 											</tr>	
											<tr>
												<td>2</td>
												<td>joe d</td>
												<td>cake</td>
												<td>Paid</td>
												<td>6</td>
												<td>
													<button class="btn btn-info">Agree</button>
													<button class="btn btn-info" id="disagree2">Disagree</button>
													<button class="btn btn-info" id="show2">Show</button>	
												</td>  
											</tr>
											<tr>
											    <td colspan="6">
												    Disagree collapse
													<div id="disagree_collapse2">
														<div class="panel-body">
															<form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label1</label>		
																	</div>
																	<div class="col-md-3 control-label">
																		<textarea placeholder="respond"class="form-control" rows="3" cols="15"></textarea>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>	
																	</div>
																	
																</div> 
															</form>
														</div>
													</div>
													show collapse
													<div id="show_collapse2">
														<div class="panel-body">
															 <form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label1</label>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label2</label>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>
																	</div>	
																	
																</div> 
															</form>
														</div>
													</div>
												</td>
 											</tr>
											<tr>
												<td>3</td>
												<td>joe d</td>
												<td>cake</td>
												<td>Paid</td>
												<td>6</td>
												<td>
													<button class="btn btn-info">Agree</button>
													<button class="btn btn-info" id="disagree3">Disagree</button>
													<button class="btn btn-info" id="show3">Show</button>	
												</td>  
											</tr>
											<tr>
											    <td colspan="6">
												    Disagree collapse
													<div id="disagree_collapse3">
														<div class="panel-body">
															<form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label1</label>		
																	</div>
																	<div class="col-md-3 control-label">
																		<textarea placeholder="respond"class="form-control" rows="3" cols="15"></textarea>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>	
																	</div>
																	
																</div> 
															</form>
														</div>
													</div>
													show collapse
													<div id="show_collapse3">
														<div class="panel-body">
															 <form class="form-horizontal">
																<div class="form-group">
																	<div class="col-md-3 pull-left">
																		<h4 style="margin-top:30px;">Franchisee respond:-</h4>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label1</label>		
																	</div>
																	<div class="col-md-2 pull-left">
																		<label style="border:1px solid #e5e5e5;padding:30px 50px;" >Label2</label>		
																	</div>
																	<div class="col-md-2 control-label">
																		<button class="btn" id="disagree_submit">Submit</button>
																	</div>	
																	
																</div> 
															</form>
														</div>
													</div>
												</td>
 											</tr>
								    </table>		
						</div>
						</div>	
						
					</div>
				</div>     
            </div>
        </div>
                END Main Content
                
            

                <a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i class="fa fa-chevron-up"></i></a>
            
            END Content
        
        END Container


        basic scripts
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="assets/jquery/jquery-2.0.3.min.js"><\/script>')</script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/jquery-cookie/jquery.cookie.js"></script>

        page specific plugin scripts
        <script type="text/javascript" src="assets/chosen-bootstrap/chosen.jquery.min.js"></script>
        <script type="text/javascript" src="assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
        <script type="text/javascript" src="assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
        <script type="text/javascript" src="assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
        <script type="text/javascript" src="assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
        <script type="text/javascript" src="assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
        <script type="text/javascript" src="assets/dropzone/downloads/dropzone.min.js"></script>
        <script type="text/javascript" src="assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
        <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>
        <script type="text/javascript" src="assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
        <script type="text/javascript" src="assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="assets/bootstrap-daterangepicker/date.js"></script>
        <script type="text/javascript" src="assets/bootstrap-daterangepicker/daterangepicker.js"></script>
        <script type="text/javascript" src="assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
        <script type="text/javascript" src="assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
        <script type="text/javascript" src="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
        <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script> 

        flaty scripts
        <script src="js/flaty.js"></script>
        <script src="js/flaty-demo-codes.js"></script>
        disagree-show accordian khemraj
	    
		<script>   
		    var a=1;
			$("#disagree_collapse"+a).slideUp(-1);
			$("#show_collapse"+a).slideUp(-1);
			       
				    function disagree(){
						$("#disagree_collapse"+a).slideToggle("slow");
						$("#show_collapse"+a).slideUp("slow");
					}
					function show(){
						$("#show_collapse"+a).slideToggle("slow");
						$("#disagree_collapse"+a).slideUp("slow");
					}
		</script>
		
    </body>
</html> -->