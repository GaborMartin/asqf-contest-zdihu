package utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Optional;
import com.testfabrik.webmate.javasdk.WebmateAPISession;
import com.testfabrik.webmate.javasdk.browsersession.BrowserSessionId;
import com.testfabrik.webmate.javasdk.testmgmt.Artifact;
import com.testfabrik.webmate.javasdk.testmgmt.ArtifactInfo;

public class DataProvider {

  public List<ActionData> getActionsFromTestRun(BrowserSessionId browserSessionId, WebmateAPISession webmateAPISession){
    List<ActionData> actions = new ArrayList<>();
    List<ArtifactInfo> artifactInfos = webmateAPISession.artifact.queryArtifacts(browserSessionId);

    for (ArtifactInfo artifactInfo: artifactInfos) {
      Optional<Artifact> artifact = webmateAPISession.artifact.getArtifact(artifactInfo.getId());
      if (artifact.get().getArtifactType().getTypeName().equals("ActionStart")){
        JsonNode jsonNode = artifact.get().getData();
        actions.add(new ActionData(jsonNode.get("name").toString().replace("\"", "")));
      }
    }
    return actions;
  }

}
