import React from "react";
import Link from "react-router/lib/Link";

export const LinkButton = ({ destinationPath, name, closeMenu}) => (
  <>
    <div class="mr-2">
      <Link
        class="text-center block border border-blue-500 rounded py-4 px-4 bg-blue-500 hover:bg-blue-900 text-white"
        to={destinationPath}
        onClick={closeMenu}
      >
        {name}
      </Link>
    </div>
  </>
);
