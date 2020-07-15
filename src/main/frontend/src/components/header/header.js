import React from "react";
import { NavigationBar } from "../navigation/navigationBar";
import Link from "react-router/lib/Link";

const Header = () => (
  <header class="flex justify-between items-center">
    <span className={"text-center text-5xl font-bold m-auto"}>
      <Link to={"/"}>InstaBlog</Link>
    </span>
    <NavigationBar />
  </header>
);

export default Header;
