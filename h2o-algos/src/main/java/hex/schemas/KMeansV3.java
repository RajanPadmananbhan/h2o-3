package hex.schemas;

import hex.kmeans.KMeans;
import hex.kmeans.KMeansModel.KMeansParameters;
import water.api.API;
import water.api.schemas3.ClusteringModelParametersSchemaV3;
import water.api.schemas3.KeyV3;

public class KMeansV3 extends ClusteringModelBuilderSchema<KMeans,KMeansV3,KMeansV3.KMeansParametersV3> {

  public static final class KMeansParametersV3 extends ClusteringModelParametersSchemaV3<KMeansParameters, KMeansParametersV3> {
    static public String[] fields = new String[] {
        "model_id",
        "training_frame",
        "validation_frame",
        "nfolds",
        "keep_cross_validation_predictions",
        "keep_cross_validation_fold_assignment",
        "fold_assignment",
        "fold_column",
        "ignored_columns",
        "ignore_const_cols",
        "score_each_iteration",
        "k",
        "estimate_k",
        "user_points",
        "max_iterations",
        "standardize",
        "seed",
        "init",
        "max_runtime_secs"
    };

    // Input fields
    @API(help = "User-specified points", required = false, level = API.Level.expert)
    public KeyV3.FrameKeyV3 user_points;

    @API(help="Maximum training iterations", gridable = true)
    public int max_iterations;        // Max iterations

    @API(help = "Standardize columns before computing distances", level = API.Level.critical, gridable = true)
    public boolean standardize = true;

    @API(help = "RNG Seed", level = API.Level.secondary /* tested, works: , dependsOn = {"k", "max_iterations"} */, gridable = true)
    public long seed;

    @API(help = "Initialization mode", values = { "Random", "PlusPlus", "Furthest", "User" }, gridable = true) // TODO: pull out of categorical class. . .
    public KMeans.Initialization init;

    @API(help = "Whether to estimate the number of clusters (<=k) iteratively and deterministically (takes longer).", level = API.Level.critical, gridable = true)
    public boolean estimate_k = false;
  }
}
