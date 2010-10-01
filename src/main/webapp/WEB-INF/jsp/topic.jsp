<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h1>A list of topics might be a good idea</h1>

<div>topic</div>
<c:forEach var="topic" items="${topics}">
  <div><a href="topic.htm?topicId=${topic.id}">${topic.name}</a></div>
  <div id="suggestions">
      <c:forEach var="suggestion" items="${topic.suggestions}">
          ${suggestion.name} &nbsp;
      </c:forEach>
  </div>
</c:forEach>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>