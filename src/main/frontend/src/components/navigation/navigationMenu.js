import React from "react";
import { LinkButton } from "../linkButton/linkButton";

function NavigationMenu(props) {
  return (
    <div>
      <ul>
      <li>
          <LinkButton
            destinationPath={"/sign-up"}
            name={"Sign Up"}
            closeMenu={props.closeMenu}
          />
        </li>
        <li>
          <LinkButton
            destinationPath={"/my-blog"}
            name={"My blog"}
            closeMenu={props.closeMenu}
          />
        </li>
        <li>
          <LinkButton
            destinationPath={"/my-profile"}
            name={"My profile"}
            closeMenu={props.closeMenu}
          />
        </li>
      </ul>
    </div>
  );
}

export default NavigationMenu;
