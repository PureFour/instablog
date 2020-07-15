import React from "react";
import "../tailwind.css";
import { MainPage } from "../components/mainPage/mainPage";
import { MyProfile } from "../components/userProfile/myProfile";
import { Router, browserHistory, Route } from "react-router";
import { Layout } from "../components/layout/layout";

function App() {
  return (
    <div className="App">
      <Layout>
        <Router history={browserHistory}>
          <Route path={"/"} component={MainPage} />
          <Route path={"my-profile"} component={MyProfile} />
        </Router>
      </Layout>
    </div>
  );
}

export default App;
