import { NavLink } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Navbar() {
  const { isAuth, authUser, logout } = useAuth();

  return (
    <nav className="navbar navbar-light">
      <div className="container">
        <NavLink className={({ isActive }) => (isActive ? "active navbar-brand" : "navbar-brand")} to="/" end>
          conduit
        </NavLink>
        <ul className="nav navbar-nav pull-xs-right">
          <li className="nav-item">
            <NavLink className={({ isActive }) => (isActive ? "active nav-link" : "nav-link")} to="/" end>
              Home
            </NavLink>
          </li>
          {isAuth ? (
            <>
              <li className="nav-item">
                <NavLink className={({ isActive }) => (isActive ? "active nav-link" : "nav-link")} to="/editor">
                  <i className="ion-compose" />
                  &nbsp;New Post
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className={({ isActive }) => (isActive ? "active nav-link" : "nav-link")} to="/settings">
                  <i className="ion-gear-a" />
                  &nbsp;Settings
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to={`/@${authUser?.username}`}>
                  <img
                    style={{ width: 24, height: 24, marginRight: 4, borderRadius: "50%" }}
                    src={authUser?.image}
                    alt={authUser?.username}
                  />
                  {authUser?.username}
                </NavLink>
              </li>
              <li className="nav-item">
                <button className="nav-link btn" onClick={logout}>
                  Logout
                </button>
              </li>
            </>
          ) : (
            <>
              <li className="nav-item">
                <NavLink className={({ isActive }) => (isActive ? "active nav-link" : "nav-link")} to="/register">
                  Sign up
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className={({ isActive }) => (isActive ? "active nav-link" : "nav-link")} to="/login">
                  Sign in
                </NavLink>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
