import React, {Component} from "react";

class SideBar extends Component {
    render() {
        return (
            <aside id="sidebar_left" class="nano nano-light affix">

                <div class="sidebar-left-content nano-content">

                    <ul class="nav sidebar-menu">
                        <li class="sidebar-label pt20">Menu</li>
                        <li class="active">
                            <a href="/dashboard">
                                <span class="glyphicon glyphicon-home"></span>
                                <span class="sidebar-title">Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="/fixtures">
                                <span class="fa fa-calendar"></span>
                                <span class="sidebar-title">Fixtures</span>
                            </a>
                        </li>
                    </ul>

                </div>

            </aside>
        );
    }
}

export default SideBar;