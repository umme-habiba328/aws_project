import {Navigate,Outlet, useNavigate} from 'react-router-dom';

const ProtectedRoutes = ({
    forRole,
    redirectPath = '/'
  }) => {
    const navigate = useNavigate();
    const isLogged = window.localStorage.getItem("isLogged");
    const role = window.localStorage.getItem("role");
    if (isLogged === "true" && role === forRole) {
      console.log(role);
      return <Outlet />;
      
    }
    return <Navigate to={redirectPath} replace />;
  };

export default ProtectedRoutes;