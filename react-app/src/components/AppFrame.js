import React, {Component} from "react";

class AppFrame extends Component {
    render() {
        return (
            <html>
            <head>
                <title>Football Prototype</title>
            </head>
            <div>
                <header className="navbar navbar-fixed-top navbar-shadow">
                    <div className="navbar-branding">
                        <a className="navbar-brand" href="dashboard">
                            <b>Shire</b>Soldiers
                        </a>
                    </div>
                    <form className="navbar-form navbar-left navbar-search alt" role="search">
                        <div className="form-group">
                            <input type="text" className="form-control" placeholder="Search..."
                                   value="Search..."/>
                        </div>
                    </form>
                    <ul className="nav navbar-nav navbar-right">
                        <li className="dropdown menu-merge">
                            <span className="caret caret-tp hidden-xs"></span>
                        </li>
                    </ul>
                </header>

                <aside id="sidebar_left" className="nano nano-light affix">

                    <div className="sidebar-left-content nano-content">

                        <ul className="nav sidebar-menu">
                            <li className="sidebar-label pt20">Menu</li>
                            <li className="active">
                                <a href="/dashboard">
                                    <span className="glyphicon glyphicon-home"></span>
                                    <span className="sidebar-title">Dashboard</span>
                                </a>
                            </li>
                            <li>
                                <a href="/fixtures">
                                    <span className="fa fa-calendar"></span>
                                    <span className="sidebar-title">Fixtures</span>
                                </a>
                            </li>
                        </ul>

                    </div>

                </aside>
            </div>
            </html>
        )
    }
}

export default AppFrame;
