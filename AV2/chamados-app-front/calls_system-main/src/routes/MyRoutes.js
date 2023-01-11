import { Routes, Route } from 'react-router-dom';
import NotFound from '../pages/NotFound';
import SignIn from '../pages/SignIn';
import SignUp from '../pages/SignUp';
import Dashboard from '../pages/Dashboard';
import Profile from '../pages/Profile';
import Costumers from '../pages/Costumers';
import New from '../pages/New';
import PrivateRoute from './PrivateRoute';

export default function MyRoutes() {
   return (
      <Routes>
         <Route path='/'          element={<SignIn/>}/>
         <Route path='/register'  element={<SignUp/>}/>
         <Route 
            path='/dashboard'
            element={
               <PrivateRoute>
                  <Dashboard/>
               </PrivateRoute>
            }
         />
         <Route 
            path='/profile'   
            element={
               <PrivateRoute>
                  <Profile/>
               </PrivateRoute>
            }
         />
         <Route 
            path='/costumers'
            element={
               <PrivateRoute>
                  <Costumers/>
               </PrivateRoute>
            }
         />
         <Route 
            path='/new'
            element={
               <PrivateRoute>
                  <New/>
               </PrivateRoute>
            }
         />
         <Route path='/*' element={<NotFound/>}/>
       </Routes> 
    );
}
