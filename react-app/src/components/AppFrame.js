import React, {Component} from "react";
import {IndexLink, Link} from "react-router";

class AppFrame extends Component {
    render() {
        return (
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
                            <li>
                                <IndexLink to="/" activeClassName="active">Dashboard</IndexLink>
                            </li>
                            <li>
                                <Link to="/fixtures" activeClassName="active">Fixtures</Link>
                            </li>
                            <li>
                                <Link to="/players" activeClassName="active">Players</Link>
                            </li>
                        </ul>

                    </div>

                </aside>
                <section id="content_wrapper">
                    <section id="content" className="table-layout animated fadeIn">
                        <div className="tray tray-center">
                            {this.props.children}
                        </div>
                    </section>
                </section>
            </div>

        )
    }
}

export default AppFrame;
