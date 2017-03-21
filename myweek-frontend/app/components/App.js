import React, {PropTypes} from 'react';
import {Navbar, Nav, NavItem} from 'react-bootstrap';
import {LinkContainer, IndexLinkContainer} from 'react-router-bootstrap';
import {content} from '../styles/app.scss';

const App = ({ children }) =>
    <div>
        <Navbar inverse collapseOnSelect fixedTop>
            <Navbar.Header>
                <Navbar.Brand>
                    <IndexLinkContainer to="/"><a href="/">myweek</a></IndexLinkContainer>
                </Navbar.Brand>
                <Navbar.Toggle />
            </Navbar.Header>
            <Navbar.Collapse>
                <Nav>
                    <IndexLinkContainer to="/"><NavItem eventKey={1} href="#">Home</NavItem></IndexLinkContainer>
                    <LinkContainer to="/users"><NavItem eventKey={2} href="#">Users</NavItem></LinkContainer>
                    <LinkContainer to="/about"><NavItem eventKey={3} href="#">About</NavItem></LinkContainer>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
        <div className={content}>
            { children }
        </div>
    </div>;

App.propTypes = {
    children: PropTypes.object
};

export default App;
