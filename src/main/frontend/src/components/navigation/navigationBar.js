import React, { useState } from "react";
import { useTransition, animated } from "react-spring";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import NavigationMenu from "./navigationMenu";

export const NavigationBar = () => {
  const [showMenu, setShowMenu] = useState(false);

  const maskTransitions = useTransition(showMenu, null, {
    from: { position: "absolute", opacity: 0 },
    enter: { opacity: 1 },
    leave: { opacity: 0 },
  });

  const menuTransitions = useTransition(showMenu, null, {
    from: { opacity: 0, transform: "translateX(+100%)" },
    enter: { opacity: 1, transform: "translateX(0%)" },
    leave: { opacity: 0, transform: "translateX(+100%)" },
  });

  return (
    <nav className={""}>
      <span className={"text-xl mr-4"}>
        <FontAwesomeIcon icon={faBars} onClick={() => setShowMenu(!showMenu)} />
      </span>
      {maskTransitions.map(
        ({ item, key, props }) =>
          item && (
            <animated.div
              key={key}
              style={props}
              className="bg-black-t-50 fixed top-0 right-0 w-full h-full z-50"
              onClick={() => setShowMenu(false)}
            ></animated.div>
          )
      )}

      {menuTransitions.map(
        ({ item, key, props }) =>
          item && (
            <animated.div
              key={key}
              style={props}
              className="fixed bg-gray-600 top-0 right-0 w-1/2 h-full z-50 shadow p-3 mr-16"
            >
              <NavigationMenu closeMenu={() => setShowMenu(false)} />
            </animated.div>
          )
      )}
    </nav>
  );
};
