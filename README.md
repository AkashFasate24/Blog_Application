# Blog_Application
<h4>Overview</h4>
</br>
This is a backend project for a Blog application built using Java Spring Boot, Spring Security, Spring Data JPA, and PostgreSQL. The application allows users to create, update, delete, and read their profiles and blogs. Users can also read others' profiles and blogs, comment on blogs, and upload/download blog images. Blogs are categorized, and only admins have access to manage categories.
<h4>Features</h4>
<ul>
<li>User Authentication and Authorization: Managed using Spring Security.</li>
<li>Profile Management: Users can create, update, delete, and read their profiles.</li>
<li>Blog Management: Users can create, update, delete, and read their blogs.</li>
<li>Commenting: Users can comment on each other's blogs.</li>
<li>Category Management: Admins can add, delete, and update blog categories.</li>
<li>Image Upload/Download: Users can upload and download images for their blogs.</li>
</ul>
<h4>Technologies Used</h4>
<ul>
    <li>Java Spring Boot: For building the backend application.</li>
    <li>Spring Security: For managing authentication and authorization.</li>
    <li>Spring Data JPA: For interacting with the PostgreSQL database.</li>
    <li>PostgreSQL: As the database.</li>
    <li>Postman: For testing the API endpoints.</li>
</ul>
<h4>API Testing</h4>
<p>Postman: Postman is used to test the API endpoints, ensuring that all functionalities work as expected. A Postman collection is provided to facilitate testing.</p
<h4>API Endpoints</h4>
</br>
</br>
<table>
    <thead>
        <tr>
            <th>Table</th>
            <th>API Endpoint</th>
            <th>Description</th>
            <th>Authentication Info</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>user</td>
            <td>http://localhost:8080/api/users/allusers</td>
            <td>Get all users or blog writers available in the database</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>user</td>
            <td>http://localhost:8080/api/users/byid/1</td>
            <td>Get specific user by ID</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>user</td>
            <td>http://localhost:8080/api/users/register</td>
            <td>Register user</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>user</td>
            <td>http://localhost:8080/api/users/update/2</td>
            <td>Update user</td>
            <td>Required: only user can update their profile with their username and password</td>
        </tr>
        <tr>
            <td>user</td>
            <td>http://localhost:8080/api/users/delete/11</td>
            <td>Delete user</td>
            <td>Required: only user can delete their profile with their username and password</td>
        </tr>
        <tr>
            <td>comment</td>
            <td>http://localhost:8080/api/post/7/comments</td>
            <td>Add comment on post by giving post ID</td>
            <td>Required: auth user should not be able to comment on other posts until they have an account</td>
        </tr>
        <tr>
            <td>comment</td>
            <td>http://localhost:8080/api/delete/7</td>
            <td>Delete comment</td>
            <td>Anyone can delete comment, requires auth</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/get/user/1/posts</td>
            <td>Get all posts written by that specific user by giving user ID</td>
            <td>No auth required</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/get/category/1/posts</td>
            <td>Get all posts within specific category by giving category ID</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/get/posts?pageNumber=0&pageSize=2&sortby=title&sortdir=desc</td>
            <td>Get all posts, with pagination, sort by option, and sorting direction</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/get/posts/6</td>
            <td>Get post by post ID</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/user/2/category/1/posts</td>
            <td>User can create post by giving user ID and category ID</td>
            <td>Auth required</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/posts/9</td>
            <td>User can update their profile</td>
            <td>Auth required, but only that user who has created the post</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/posts/7</td>
            <td>Delete post by giving post ID</td>
            <td>Auth required, only the user who created the post can delete it</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/get/posts/search/travel</td>
            <td>Search post by title</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>post</td>
            <td>http://localhost:8080/api/post/image/upload/7</td>
            <td>Upload image by giving post ID</td>
            <td>Auth required, user can only upload image to their blog, not others</td>
        </tr>
        <tr>
            <td>category</td>
            <td>http://localhost:8080/api/category/get/1</td>
            <td>Get category by category ID</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>category</td>
            <td>http://localhost:8080/api/category/get/all</td>
            <td>Get all categories</td>
            <td>No auth</td>
        </tr>
        <tr>
            <td>category</td>
            <td>http://localhost:8080/api/category/1</td>
            <td>Create category</td>
            <td>Auth required, only admin has access to create category</td>
        </tr>
        <tr>
            <td>category</td>
            <td>http://localhost:8080/api/category/14/1</td>
            <td>Update category</td>
            <td>Auth required, only admin has access to update category</td>
        </tr>
        <tr>
            <td>category</td>
            <td>http://localhost:8080/api/category/14/1</td>
            <td>Delete category</td>
            <td>Auth required, only admin has access to delete category</td>
        </tr>
    </tbody>
</table>

         
    



